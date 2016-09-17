/**
 * Express configuration
 */

'use strict';

import bodyParser from 'body-parser';
import express from 'express';
import path from 'path';
import errors from '../components/errors';

export default function(app) {
  app.use(bodyParser.json());

  app.use(function(err, req, res, next) {
    if (err instanceof SyntaxError && err.status === 400 && 'body' in err) {
      errors.invalidJson(req, res);
    }
  });

  let folder = path.join(__dirname, '/../swagger');
  app.use('/swagger', express.static(folder));
}
