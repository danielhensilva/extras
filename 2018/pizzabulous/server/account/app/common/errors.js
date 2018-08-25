class AppError extends Error {
  constructor(status, description) {
    super();
    this.safe = true;
    this.status = status;
    this.description = description;
  }
}

module.exports.UsernameInUseError = (function() {
  return new AppError(400, 'username-already-in-use');
});

module.exports.UsernameOrPaswordIncorrectError = (function() {
  return new AppError(400, 'username-or-password-incorrect');
});

module.exports.AuthTokenInvalidError = (function() {
  return new AppError(401, 'auth-token-invalid');
});