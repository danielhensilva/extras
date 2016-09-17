'use strict';

import errors from '../errors';
import User from '../../api/user/user.model';
import config from '../../config/environment';
import Promise from 'bluebird';
import jwt from 'jsonwebtoken';

function findUserByIdAndToken(userId, userToken, response) {
  return new Promise((resolve, reject) => {
    User.findById(userId, (error, user) => {
      if (error) {
        errors.unathorized(null, response);
        reject();
        return;
      }
      if (user) {
        if (user.token.toString() !== userToken.toString()) {
          errors.invalidToken(null, response);
          reject();
          return;
        }
      }
      resolve(user);
    });
  });
}

function findUserByEmail(email, response) {
  return new Promise((resolve, reject) => {
    User.findOne({email: email})
      .then(user => {
        if (user) {
          resolve(user);
          return;
        }
        errors.invalidCredentials(null, response);
        reject();
      })
      .catch(error => {
        errors.generic(null, response);
      });
  });
}

function generateToken(user, expiresIn) {
  const halfHour = 60 * 60 * 30;
  return jwt.sign({_id: user._id}, config.secrets.session, {
    expiresIn: expiresIn || halfHour
  });
}

function extractToken(headers, response) {
  return new Promise((resolve, reject) => {
    if (headers) {
      let bearer = headers.authentication;
      if (bearer) {
        let groups = (/^Bearer (.+)$/).exec(bearer);
        if (groups && groups.length === 2) {
          let token = groups[1];
          resolve(token);
          return;
        }
      }
    }
    errors.invalidToken(null, response);
    reject();
  });
}

function checkToken(token, response) {
  return new Promise((resolve, reject) => {
    jwt.verify(token, config.secrets.session, function(error, decoded) {
      if (error === null) {
        resolve(decoded._id);
      } else if (error.name === "JsonWebTokenError") {
        errors.invalidToken(null, response);
        reject(error);
      } else if (error.name === "TokenExpiredError") {
        errors.expiredToken(null, response);
        reject(error);
      } else {
        errors.generic(null, response);
        reject(error);
      }
    });
  });
}

export function refreshToken(user, response) {
  user.ultimoLogin = new Date();
  user.token = generateToken(user);
  return user.update({
    ultimoLogin: user.ultimoLogin,
    token: user.token
  })
  .catch(error => {
    errors.generic(null, response, error);
  }).then(() => {
    return user;
  });
}

export function checkPassword(user, password, response) {
  return new Promise((resolve, reject) => {
    let ok = user.checkPassword(password);
    if (ok) {
      resolve(user);
      return;
    }
    errors.invalidCredentials(null, response);
    reject();
  });
}

export function authenticate(req, res, next) {
  let credentials = {
    email: req.body.email,
    senha: req.body.senha
  };

  let promise = findUserByEmail(credentials.email, res);

  promise = promise.then(user => {
    return checkPassword(user, credentials.senha, res);
  });

  promise = promise.then(user => {
    return refreshToken(user, res);
  });

  promise = promise.then(user => {
    res.json(user.toObject());
  });
}

export function isAuthenticated(req, res, next) {
  let userToken = null;
  let promise = extractToken(req.headers, res);

  promise = promise.then(token => {
    userToken = token;
    return checkToken(token, res);
  });

  promise = promise.then(userId => {
    return findUserByIdAndToken(userId, userToken, res);
  });

  promise = promise.then(user => {
    req.locals = req.locals || {};
    req.locals.user = user;
    next();
  });
}
