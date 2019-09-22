const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const User = require('../models/user');
const errors = require('../common/errors');
const configs = require('../../configs');

module.exports.signUp = async function signUp(username, plainTextPassword) {
  const existingUser = await getUser(username);
  if (!existingUser) {
    throw new errors.UsernameInUseError();
  }

  const hashedPassword = await bcrypt.hash(plainTextPassword, 12);
  const newUser = await User.create({username, hashedPassword});
  return await signToken(newUser);
};

module.exports.signIn = async function signIn(username, plainTextPassword) {
  const user = await getUser(username);
  if (!user) {
    throw new errors.UsernameOrPaswordIncorrectError();
  }

  const match = await bcrypt.compare(plainTextPassword, user.hashedPassword);
  if (!match) {
    throw new errors.UsernameOrPaswordIncorrectError();
  }

  return await signToken(user);
};

module.exports.me = async function signIn(token) {
  try {
    return await jwt.verify(token, configs.jwt.secret);
  } catch (error) {
    console.log(error);
    throw new errors.AuthTokenInvalidError();
  }
};

async function getUser(username) {
  const user = await User.find({where: {username}});
  if (user && user.id) {
    return user;
  }
  return null;
}

async function signToken(user) {
   return await jwt.sign({
     id: user.id,
     username: user.username
   }, configs.jwt.secret);
}

