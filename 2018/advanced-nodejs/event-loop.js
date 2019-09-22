const fs = require('fs');

// any function that returns asynchronous results
// should validate input asynchronously as well

function fileSize(fileName, callback) {
    if (typeof fileName !== 'string') {
        // In order to validate this content asynchronous
        // we need to call `process.nextTick`, so it will
        // be pushed to event-loop
        return process.nextTick(callback, new TypeError('argument should be string'));
    }

    fs.stat(fileName, (err, stats) => {
        if (err) return callback(err);
        callback(null, stats.size);
    });
}

fileSize(__filename, (err, size) => {
    // will asynchronously succeed
    console.log(`Size in KB: ${size/1024}`);
});

fileSize(123, (err, size) => {
    // will asynchronously fail
    if (err) throw err;
});

console.info("Done...");
