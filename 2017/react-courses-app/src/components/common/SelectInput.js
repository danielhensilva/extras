import React, {PropTypes} from 'react';
import Errors from './Errors';

const SelectInput = ({name, label, onChange, defaultOption, value, error, options}) => {
  const wrapperClass = `form-group ${error && error.length ? 'hasError' : ''}`;
  return (
    <div className={wrapperClass}>
      <label htmlFor={name}>{label}</label>
      <div className="field">
        <select
          name={name}
          value={value}
          onChange={onChange}
          className="form-control">
          <option value="">{defaultOption}</option>
          {options.map(x => <option key={x.value} value={x.value}>{x.text}</option>)}
        </select>
        <Errors error={error} />
      </div>
    </div>
  );
};

SelectInput.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  defaultOption: PropTypes.string,
  value: PropTypes.string,
  error: PropTypes.string,
  options: PropTypes.arrayOf(PropTypes.object)
};

export default SelectInput;
