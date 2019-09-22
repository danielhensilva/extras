const dns = require('dns');

dns.lookup('github.com', (error, address) => {
    console.log("Lookup")
    console.log(address)
})

dns.resolve4('pluralsight.com', (error, address) => {
    console.log("resolve4")
    console.log(address)
})

dns.resolveMx('pluralsight.com', (error, address) => {
    console.log("resolveMx")
    console.log(address)
})

dns.reverse('8.8.8.8', (error, address) => {
    console.log("reverse")
    console.log(address)
})

