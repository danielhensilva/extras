const { Transform } = require('stream')

const upperCaseTransform = new Transform({
    transform(chunk, encoding, callback) {
        this.push(chunk.toString().toUpperCase())
        callback()
    }
})

const reverseTransform = new Transform({
    transform(chunk, encoding, callback) {
        this.push(chunk.toString().split('').reverse().splice(2).concat('\n').concat('\r').join(''))
        callback()
    }
})

process.stdin
    .pipe(upperCaseTransform)
    .pipe(reverseTransform)
    .pipe(process.stdout)