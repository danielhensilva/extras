const env = (process.env.NODE_ENV === 'production' ? 'prod' : 'dev');
module.exports = require(`./configureStore.${env}`);