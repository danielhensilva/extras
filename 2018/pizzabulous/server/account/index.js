const express = require('express');
const app = express();

app.use('/v1/auth', require('app/routes/auth.v1'));

app.use((err, req, res, next) => {
  if (err) {
    console.error(err.stack);
    if (err.safe) {
      res.status(err.status);
      res.send(err.description);
      return;
    }
  }
  next(err);
});

app.listen(8080, () =>
  console.log('Application running at port 8080...')
);