import expect from 'expect';
import {createStore} from 'redux';
import rootReducer from '../reducers';
import initialState from '../reducers/initialState';
import * as courseActions from '../actions/courseActions';

describe('Store', () => {
  it('should handle creating courses', () => {
    const store = createStore(rootReducer, initialState);
    const course = { title: 'Clean Code' };
    const action = courseActions.createCourseSuccess(course);
    store.dispatch(action);
    const actual = store.getState().courses[0];
    const expected = { title: 'Clean Code' };
    expect(actual).toEqual(expected);
  });
});