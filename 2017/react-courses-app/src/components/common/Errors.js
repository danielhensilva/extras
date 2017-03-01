import React, {PropTypes} from 'react';

const Errors = ({error}) => {
  if (error && error.length)
    return <div className="alert alert-danger">{error}</div>;
  return null;
};

Errors.propTypes = {
  error: PropTypes.string
};

export default Errors;