'use strict';

export default function(app) {
  app.use('/auth', require('./components/auth'));
  app.use('/api/users', require('./api/user'));
  app.all('*', require('./components/errors').endpointNotFound);
}
