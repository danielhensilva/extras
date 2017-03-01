import React from 'react';
import expect from 'expect';
import {mount, shallow} from 'enzyme';
import {ManageCoursePage} from './ManageCoursePage';

function generateProps() {
  return {
    authors: [],
    actions: {
      saveCourse: () => Promise.resolve()
    },
    course: {
      id: '',
      title: '',
      length: '',
      authorId: '',
      category: '',
      watchHref: ''
    }
  };
}

describe('Manage Course Page', () => {
  it('sets error message when trying to save empty title', () => {
    const props = generateProps();
    const $ = mount(<ManageCoursePage {...props}/>);
    const saveButton = $.find('input').last();
    expect(saveButton.prop('type')).toBe('submit');
    saveButton.simulate('click');
    expect($.state().errors.title).toBe('Title must be at least 5 characters.');
  });
});