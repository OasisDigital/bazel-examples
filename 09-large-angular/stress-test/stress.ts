import { generate } from './generate';

const args = process.argv.slice(2)

if (args.length == 3) {
  const dir = args[0];
  const n = parseInt(args[1]);
  const c = parseInt(args[2]);
  console.log(`Generating ${n} modules, ${c} components`);
  generate(dir, n, c, true, true);
}
