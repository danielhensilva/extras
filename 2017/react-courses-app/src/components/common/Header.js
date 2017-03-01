import React, {PropTypes} from 'react';
import { Link, IndexLink } from 'react-router';
import Loading from './Loading';

const Header = ({loading}) => {
  return (
    <nav>
      <IndexLink to="/" activeClassName="active">Home</IndexLink>
      <Link to="/courses" activeClassName="active">Courses</Link>
      <Link to="/about" activeClassName="active">About</Link>
      {loading && <Loading/>}
    </nav>
  );
};

Header.propTypes = {
  loading: PropTypes.bool.isRequired
};

export default Header;