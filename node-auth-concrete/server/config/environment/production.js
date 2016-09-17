'use strict';
/* eslint no-process-env:0*/

// Production specific configuration
// =================================
module.exports = {
  // MongoDB connection options
  mongo: {
    uri: process.env.MONGODB_URI ||
          process.env.MONGOHQ_URL ||
         'mongodb://localhost/nodeconcrete'
  }
};
