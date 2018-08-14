const serverless = require("serverless-http");
const bodyParser = require("body-parser");
const express = require("express");
const aws = require("aws-sdk");

const validator = require("./app/common/validators");
const credentials = require("./app/security/credentials");

const app = express();
app.use(bodyParser.json({strict: false}));

app.get('/', (req, res) => {
  res.status(200).json('Hello, world!');
});

app.post('/users', (req, res) => {
  const { email, name, password } = req.body;

  const errors = validator.createUser(email, name, password);
  if (errors.length) {
    res.status(400).json(errors);
    return;
  }

  const createUserParams = {
    
  };

  const provider = new aws.CognitoIdentityServiceProvider({
  });

  provider.adminCreateUser(createUserParams, createUserCallback);
  
  function createUserCallback() {

  }

});

// module.exports.handler = serverless(app);

app.listen(3000, () => console.log('Example app listening on port 3000'));