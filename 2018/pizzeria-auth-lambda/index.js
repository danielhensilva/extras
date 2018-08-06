const serverless = require("serverless-http");
const bodyParser = require("body-parser");
const express = require("express");
const awsCognitoIdentity = require("amazon-cognito-identity-js");
const validator = require("./app/validators");
const credentials = require("./app/credentials");

const app = express();
app.use(bodyParser.json({strict: false}));

app.get('/', (req, res) => {
  res.status(200).json('Hello, world!');
});

app.post('/users', (req, res) => {
  const { email, name, password } = req.body;

  const errors = validator.createUser(email, name, password);
  if (errors.length) {
    res.status(400).json({error: errors});
    return;
  }

  const userPool = new awsCognitoIdentity.CognitoUserPool({
    UserPoolId: credentials.AWS.Cognito.UserPoolId,
    ClientId: credentials.AWS.Cognito.ClientId,
  });

  const userAttributes = new awsCognitoIdentity.CognitoUserAttribute({
    Name: name,
    Email: email
  });

  userPool.signUp(email, password, [userAttributes], null, (error, result) => {
    if (error) {
      res.status(400).json({error});
    }

    res.status(200).json(`user "${result.user.getUsername()}" created`);
  });
});

module.exports.handler = serverless(app);
