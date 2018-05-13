const http = require('http');
const server = http.createServer();

server.on('request', (req, res) => {
    res.writeHead(200, { 'content-type': 'text/plain' })
    res.write('Some message');
    res.end();
});

server.timeout = 2000;
server.listen(8080, () => console.log('Running at port 8080...'));