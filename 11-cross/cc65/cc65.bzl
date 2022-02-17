"""
Toolchain implementation for cc65

This example intentionally targets a toolchain simpler and different from today's
popular tools, suitable for understanind.

cc65 is a 6502 compiler, assembler, and linker, suitable to build software for
"retro gaming", though also likely in production use - because 6502 chips are
in use, in various modified forms, in cheap microcontroller-like settings.

https://cc65.github.io/

"""

load(
    "@bazel_tools//tools/cpp:cc_toolchain_config_lib.bzl",
    "action_config",
    "feature",
    "flag_group",
    "flag_set",
    "tool",
    "tool_path",
    "variable_with_value",
    "with_feature_set",
)
load("@bazel_tools//tools/build_defs/cc:action_names.bzl", "ACTION_NAMES")

# cc65 binaries not yet in use by this toolchain:
# ca65
# chrcvt65
# da65
# grc65
# sim65

no_legacy_features_feature = feature(name = "no_legacy_features")

include_paths_feature = feature(
    name = "include_paths",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = [
                        "-I",
                        "%{quote_include_paths}",
                    ],
                    iterate_over = "quote_include_paths",
                    expand_if_available = "quote_include_paths",
                ),
                flag_group(
                    flags = [
                        "-I",
                        "%{system_include_paths}",
                    ],
                    iterate_over = "system_include_paths",
                    expand_if_available = "system_include_paths",
                ),
                flag_group(
                    flags = [
                        "-I",
                        "%{include_paths}",
                    ],
                    iterate_over = "include_paths",
                    expand_if_available = "include_paths",
                ),
            ],
        ),
    ],
)

preprocessor_defines_feature = feature(
    name = "preprocessor_defines",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = ["-D%{preprocessor_defines}"],
                    iterate_over = "preprocessor_defines",
                ),
            ],
        ),
    ],
)

dependency_file_feature = feature(
    name = "dependency_file",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = ["--create-dep", "%{dependency_file}"],
                    expand_if_available = "dependency_file",
                ),
            ],
        ),
    ],
)

# From cc65 --list-warnings
cc65_warnings = [
    "const-comparison",
    "error",
    "no-effect",
    "remap-zero",
    "struct-param",
    "unknown-pragma",
    "unused-label",
    "unused-param",
    "unused-var",
]

warnings_feature = feature(
    name = "warnings",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = [
                        "-W",
                        ",".join(cc65_warnings),
                    ],
                ),
            ],
        ),
    ],
)

user_compile_flags_feature = feature(
    name = "user_compile_flags",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = ["%{user_compile_flags}"],
                    iterate_over = "user_compile_flags",
                    expand_if_available = "user_compile_flags",
                ),
            ],
        ),
    ],
)

opt_feature = feature(name = "opt")

# TODO: support the "dbg" feature also.

compiler_output_flags_feature = feature(
    name = "compiler_output_flags",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [ACTION_NAMES.c_compile],
            with_features = [with_feature_set(features = ["opt"])],
            flag_groups = [flag_group(flags = ["-O"])],
        ),
        flag_set(
            actions = [ACTION_NAMES.c_compile],
            flag_groups = [
                flag_group(
                    flags = ["-S"],
                    expand_if_available = "output_assembly_file",
                ),
                flag_group(
                    flags = ["-o", "%{output_file}"],
                    expand_if_available = "output_file",
                ),
            ],
        ),
    ],
)

compiler_inputs_feature = feature(
    name = "compiler_inputs",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [
                ACTION_NAMES.c_compile,
            ],
            flag_groups = [
                flag_group(
                    flags = [
                        "-c",  # Compile and assemble, but don't link
                        "%{source_file}",  # Single C file at a time.
                    ],
                    expand_if_available = "source_file",
                ),
            ],
        ),
    ],
)

output_execpath_flags_feature = feature(
    name = "output_execpath_flags",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [ACTION_NAMES.cpp_link_executable],
            flag_groups = [
                flag_group(
                    flags = ["-o", "%{output_execpath}"],
                    expand_if_available = "output_execpath",
                ),
            ],
        ),
    ],
)

link_flags_feature = feature(
    name = "link_flags",
    enabled = True,
    flag_sets = [
        flag_set(
            actions = [ACTION_NAMES.cpp_link_executable],
            flag_groups = [
                flag_group(
                    flags = ["-L%{library_search_directories}"],
                    iterate_over = "library_search_directories",
                    expand_if_available = "library_search_directories",
                ),
            ],
        ),
        flag_set(
            actions = [ACTION_NAMES.cpp_link_executable],
            flag_groups = [
                flag_group(
                    iterate_over = "libraries_to_link",
                    flag_groups = [
                        flag_group(
                            iterate_over = "libraries_to_link.object_files",
                            flag_groups = [
                                flag_group(
                                    flags = ["%{libraries_to_link.object_files}"],
                                    expand_if_false = "libraries_to_link.is_whole_archive",
                                ),
                                flag_group(
                                    flags = ["-Wl,-force_load,%{libraries_to_link.object_files}"],
                                    expand_if_true = "libraries_to_link.is_whole_archive",
                                ),
                            ],
                            expand_if_equal = variable_with_value(
                                name = "libraries_to_link.type",
                                value = "object_file_group",
                            ),
                        ),
                        flag_group(
                            flag_groups = [
                                flag_group(
                                    flags = ["%{libraries_to_link.name}"],
                                    expand_if_false = "libraries_to_link.is_whole_archive",
                                ),
                                flag_group(
                                    flags = ["-Wl,-force_load,%{libraries_to_link.name}"],
                                    expand_if_true = "libraries_to_link.is_whole_archive",
                                ),
                            ],
                            expand_if_equal = variable_with_value(
                                name = "libraries_to_link.type",
                                value = "object_file",
                            ),
                        ),
                        flag_group(
                            flag_groups = [
                                flag_group(
                                    flags = ["%{libraries_to_link.name}"],
                                    expand_if_false = "libraries_to_link.is_whole_archive",
                                ),
                                flag_group(
                                    flags = ["-Wl,-force_load,%{libraries_to_link.name}"],
                                    expand_if_true = "libraries_to_link.is_whole_archive",
                                ),
                            ],
                            expand_if_equal = variable_with_value(
                                name = "libraries_to_link.type",
                                value = "static_library",
                            ),
                        ),
                    ],
                    expand_if_available = "libraries_to_link",
                ),
            ],
        ),
    ],
)

def _impl(ctx):
    target_system_name = ctx.attr.target

    target_feature = feature(
        name = "target",
        enabled = True,
        flag_sets = [
            flag_set(
                actions = [
                    ACTION_NAMES.c_compile,
                    ACTION_NAMES.cpp_link_executable,
                ],
                flag_groups = [flag_group(flags = [
                    "--target",
                    target_system_name,
                ])],
            ),
        ],
    )

    target_lib_feature = feature(
        name = "target_lib",
        enabled = True,
        flag_sets = [
            flag_set(
                actions = [
                    ACTION_NAMES.cpp_link_executable,
                ],
                flag_groups = [flag_group(flags = [
                    "%s.lib" % target_system_name,
                ])],
            ),
        ],
    )

    features = [
        no_legacy_features_feature,
        opt_feature,
        target_feature,
        include_paths_feature,
        preprocessor_defines_feature,
        dependency_file_feature,
        warnings_feature,
        user_compile_flags_feature,
        compiler_output_flags_feature,
        compiler_inputs_feature,
        output_execpath_flags_feature,
        link_flags_feature,
        target_lib_feature,
    ]

    return [
        # Tip: search "create_cc_toolchain_config_info" in the Bazel source.
        cc_common.create_cc_toolchain_config_info(
            ctx = ctx,
            features = features,
            action_configs = [
                action_config(
                    action_name = ACTION_NAMES.c_compile,
                    implies = [],
                    tools = [
                        # use cl65 as compiler, because cc65 emits
                        # assembly and needs ca65; Bazel's
                        # cc_toolchain assumes that a compiler emits
                        # .o files.
                        tool(path = ctx.attr.binpath + "/cl65"),
                    ],
                ),
                action_config(
                    action_name = ACTION_NAMES.cpp_link_executable,
                    implies = [],
                    tools = [
                        tool(path = ctx.attr.binpath + "/ld65"),
                    ],
                ),
                action_config(
                    # Required, but not used in simple use case
                    action_name = ACTION_NAMES.strip,
                    implies = [],
                    tools = [
                        # No specific strip command for cc65
                        tool(path = ctx.attr.binpath + "/strip"),
                    ],
                ),
            ],
            toolchain_identifier = "cc65-toolchain",
            host_system_name = "local",
            target_system_name = target_system_name,
            target_cpu = "6502",
            target_libc = "nothing",
            cc_target_os = "nothing",
            compiler = "cc65 V2.18",
            abi_version = "unknown",
            abi_libc_version = "unknown",
            tool_paths = [
                tool_path(
                    name = "ar",
                    path = ctx.attr.binpath + "/ar65",
                ),
                tool_path(
                    name = "cpp",
                    path = ctx.attr.binpath + "/cc65",
                ),
                tool_path(
                    name = "gcc",
                    path = ctx.attr.binpath + "/cl65",
                ),
                tool_path(
                    name = "gcov",
                    path = ctx.attr.binpath + "/co65",
                ),
                tool_path(
                    name = "ld",
                    path = ctx.attr.binpath + "/ld65",
                ),
                tool_path(
                    name = "nm",
                    # Can't find this for cc65
                    path = ctx.attr.binpath + "/",
                ),
                tool_path(
                    name = "objdump",
                    path = ctx.attr.binpath + "/od65",
                ),
                tool_path(
                    name = "strip",
                    path = ctx.attr.binpath + "/sp65",
                ),
            ],
        ),
    ]

cc65_toolchain_config = rule(
    implementation = _impl,
    attrs = {
        "binpath": attr.string(mandatory = True),
        "target": attr.string(mandatory = True),
    },
    provides = [CcToolchainConfigInfo],
)

# compare to very minimal official example:
# https://github.com/bazelbuild/rules_cc/tree/main/examples/custom_toolchain

# TODO:
# linker mapfile output, -m example.map
