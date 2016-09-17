'use strict';

import * as auth from '../../components/auth/auth.service';
import errors from '../../components/errors';
import User from './user.model';
import Promise from 'bluebird';

function findUserById(id, response) {
  return new Promise((resolve, reject) => {
    User.findById(id, (error, user) => {
      if (error) {
        errors.unathorized(null, response);
        reject();
        return;
      }
      resolve(user);
    });
  });
}

function validate(user, response) {
  return new Promise((resolve, reject) => {
    user.validate(issues => {
      if (issues) {
        errors.invalidInput(null, response, issues);
        reject(issues);
      }
      resolve(user);
    });
  });
}

function createUser(user, response) {
  user.senha = user.hashPassword();
  return user.save()
    .catch(error => {
      errors.generic(null, response, error);
    })
    .then(() => {
      return user;
    });
}

export function create(req, res) {
  let user = new User(req.body);
  let promise = validate(user, res);

  promise = promise.then(() => {
    return createUser(user, res);
  });

  promise = promise.then(() => {
    return auth.refreshToken(user, res);
  });

  promise = promise.then(() => {
    res.json(user.toObject());
  });
}

export function show(req, res) {
  let authUser = req.locals.user;
  let promise = findUserById(req.query.userId, res);
  promise.then(user => {
    if (user && user._id.toString() === authUser._id.toString()) {
      res.json(user.toObject());
      return;
    }
    errors.unathorized(null, res);
  });
}
