const fs = require('fs');
const https = require('http');

// generate a new certificate...
// openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -nodes

const server = https.createServer({
    key: fs.readFileSync('./ssl/key.pem'),
    cert: fs.readFileSync('./ssl/cert.pem')
});

server.on('request', (req, res) => {
    res.writeHead(200, { 'content-type': 'text/plain' })
    res.write('Some message');
    res.end();
});

server.timeout = 2000;
server.listen(443, () => console.log('Running at port 443...'));

// testing...
// curl https://localhost/