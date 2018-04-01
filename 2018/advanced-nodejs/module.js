// Module package
console.log(module);

// Seeing module wrapper function with (exports, require, module) parameters
console.log(require("module").wrapper);


// Consuming addons packages
// check addon folder
const addon = require("addon");
console.log(addon.hello());


// Trick to export as function or as command line app

const print = (header) => {
    console.log('*'.repeat(header.length));
    console.log(header);
    console.log('*'.repeat(header.length));
};

// process.argv
// [0] = node.exe
// [1] = event-loop.js
// [*] = additional arguments

if (require.main == module) {
    // Running as a script
    // -> Calling function directly
    print(process.argv[2]);
}
else {
    // Being required
    // -> exporting function
    module.exports = {print};
}

// Console
//  node module 'Daniel' 
//  node -p "require('./module').print('Daniel')"