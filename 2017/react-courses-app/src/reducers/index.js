import {combineReducers} from 'redux';
import courses from './courseReducer';
import authors from './authorReducer';
import ajaxStatus from './ajaxStatusReducer';

const rootReducer = combineReducers({
  courses,
  authors,
  ajaxStatus
});

export default rootReducer;