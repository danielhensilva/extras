const express = require('express');

const app = express();

app.use('/user', require('./user'));

app.listen(8080, () =>
  console.log('Application running at port 8080...')
);