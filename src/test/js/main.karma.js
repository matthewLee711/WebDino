/*eslint-disable*/
var tests = [];
for (var file in window.__karma__.files) {
  if (window.__karma__.files.hasOwnProperty(file)) {
    if (/\/.*Spec\.js$/.test(file) && !/node_modules/.test(file)) {
      tests.push(file);
    }
  }
}

for (var file in window.__karma__.files) {
  window.__karma__.files[file.replace(/^\//, '')] = window.__karma__.files[file];
}

requirejs.config({
  // Karma serves files from '/base'
  //this is the requirejs root path. so when you do something like

  //its relative to this path
  baseUrl: './base/js/',
  //appDir:'',

  //By default load any module IDs from js/lib
  paths: {
    'jquery': '../node_modules/jquery/dist/jquery',
    'khantroller': '../node_modules/khantroller/dist/khantroller'
  },
  shim: {
  },
  // ask Require.js to load these files (all our tests)
  deps: tests,

  // start test run, once Require.js is done
  callback: window.__karma__.start
});
/*eslint-enable*/
