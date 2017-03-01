import * as types from '../actions/actionTypes';
import courseApi from '../api/mockCourseApi';
import {beginAjaxCall, ajaxCallError} from './ajaxStatusActions';

export function loadCoursesSuccess(courses) {
  return { type: types.LOAD_COURSES_SUCCESS, courses };  
}

export function updateCourseSuccess(course) {
  return { type: types.UPDATE_COURSES_SUCCESS, course };  
}

export function createCourseSuccess(course) {
  return { type: types.CREATE_COURSES_SUCCESS, course };  
}

export function loadCourses() {
  return dispatch => {
    dispatch(beginAjaxCall());
    return courseApi
      .getAllCourses()
      .then(data => {
        dispatch(loadCoursesSuccess(data));
      })
      .catch(error => { 
        throw(error); 
      });
  };
}

export function saveCourse(course) {
  return (dispatch, getState) => {
    dispatch(beginAjaxCall());
    return courseApi
      .saveCourse(course)
      .then(savedCourse => {
        if (course.id) {
          dispatch(updateCourseSuccess(savedCourse));
        }
        else {
          dispatch(createCourseSuccess(savedCourse));
        }
      })
      .catch(error => {
        dispatch(ajaxCallError(error));
        throw(error);
      });
  };
}