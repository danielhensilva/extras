import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function courseReducer(state = initialState.courses, action) {
  switch(action.type) {
    case types.UPDATE_COURSES_SUCCESS:
      return [
        ...state.filter(x => x.id !== action.course.id),
        Object.assign({}, action.course)
      ];
    case types.CREATE_COURSES_SUCCESS:
      return [
          ...state,
          Object.assign({}, action.course)
      ];    
    case types.LOAD_COURSES_SUCCESS:
      return action.courses;
    default:
      return state;
  }
}