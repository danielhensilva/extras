import React, {PropTypes} from 'react';
import Errors from './Errors';

const TextInput = ({name, label, onChange, placeholder, value, error}) => {
  const wrapperClass = `form-group ${error && error.length ? 'hasError' : ''}`;

  return (
    <div className={wrapperClass}>
      <label htmlFor={name}>{label}</label>
      <div className="field">
        <input
          type="text"
          name={name}
          className="form-control"
          placeholder={placeholder}
          value={value}
          onChange={onChange} />
        <Errors error={error} />
      </div>
    </div>
  );
};

TextInput.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  placeholder: PropTypes.string,
  value: PropTypes.string,
  error: PropTypes.string
};

export default TextInput;
