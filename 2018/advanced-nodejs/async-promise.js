const fs = require('fs');

// asynchronous function that works with callback and promise
const readFileAsArray = function(file, callback = () => {}) {
    return new Promise((resolve, reject) => {
        fs.readFile(file, function(error, data) {
            if (error) {
                reject(error);
                return callback(error);
            }
            
            const lines = data.toString().trim().split('\n');
            resolve(lines);
            callback(null, lines);
        });
    });
};

const numberOfComments = function(lines) {
    const isComment = line => line.match(/^ *\/\//);
    const comments = lines.filter(isComment);
    console.log(comments);
    return comments.length;
};

// promise style
readFileAsArray(__filename)
    .then(lines => {
        console.log(`There is ${numberOfComments(lines)} commented out lines`);
    })
    .catch(error => {
        console.error(error);
    });

// callback style
readFileAsArray(__filename, (error, lines) => {
    if (error) {
        console.error(error);
    }
    else {
        console.log(`There is ${numberOfComments(lines)} commented out lines`);
    }
});