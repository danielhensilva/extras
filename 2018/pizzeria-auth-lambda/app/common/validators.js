const validator = require("validator");

module.exports = {

  createUser: (email, name, password) => {
    const errors = [];
    isName(name, errors);
    isEmail(email, errors);
    isPassword(password, errors);
    return errors;
  }

};

function isPassword(password, errors) {
  if (!validator.isLength(password, {min: 8})) {
    errors.push("password must have at least 8 characters long");
  }
  if (!validator.matches(password, /[A-Z]/)) {
    errors.push("password must have at least one uppercase letter");
  }
  if (!validator.matches(password, /[a-z]/)) {
    errors.push("password must have at least one lowercase letter");
  }
  if (!validator.matches(password, /[0-9]/)) {
    errors.push("password must have at least one number");
  }
}

function isName(name, errors) {
  if (!validator.isLength(name, {min: 3})) {
    errors.push("name must be at least 3 characters long");
  }
  if (validator.matches(name, /[^a-zA-Z ]/)) {
    errors.push("name must contain only letters and blank space");
  }
}

function isEmail(email, errors) {
  if (!validator.isEmail(email)) {
    errors.push('email must be a valid email');
  }
}
