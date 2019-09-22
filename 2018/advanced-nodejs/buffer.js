// Safe vrs Unsafe

console.log("alloc: (" + Buffer.alloc(8) + ")");
console.log("allocUnsafe: (" + Buffer.allocUnsafe(8) + ")");
console.log();

// Different representations

const string = "touche";
const buffer = Buffer.from("touche");

console.log(string, string.length);
console.log(buffer, buffer.length);
console.log();

// Shared memory

const fs = require("fs");

const conversionMap = {
    "88": "65",
    "89": "66",
    "90": "67",
};

fs.readFile(__filename, (error, buffer) => {
    // receives a shared copy of original buffer
    let tag = buffer.slice(-4, -1);
    
    for (let i = 0; i < tag.length; i++) {
        tag[i] = conversionMap[tag[i]];
    }

    // original buffer has changed as well
    console.log(buffer.toString());
});

// TAG: XYZ