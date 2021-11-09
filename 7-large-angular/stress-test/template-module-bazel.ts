export function moduleBuildContents(
  name: string,
  sourcefileNames: string[],
  assetFileNames: string[]
) {
  const str = (data) => JSON.stringify(data, undefined, 2);

  return `load("//tools:angular_ts_project.bzl", "ng_ts_project")

package(default_visibility = ["//:__subpackages__"])

ng_ts_project(
    name = ${str(name)},
    srcs = ${str(sourcefileNames)},
    angular_assets = ${str(assetFileNames)},
    deps = [
      "@npm//@angular/core",
      "@npm//@angular/common",
  ],
)
`;
}
