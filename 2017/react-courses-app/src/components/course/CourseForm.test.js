import expect from 'expect';
import React from 'react';
import {mount, shallow} from 'enzyme';
import TestUtils from 'react-addons-test-utils';
import CourseForm from './CourseForm';

function setup(saving = false) {
  const props = {
    course: {}, 
    saving: saving, 
    errors: {},
    onSave: () => {},
    onChange: () => {}
  };
  
  return shallow(<CourseForm {...props} />);
}

describe('CourseForm via Enzyme', () => {

  it('renders form and h1', () => {
    const $ = setup();
    expect($.find('form').length).toBe(1);
    expect($.find('h1').text()).toEqual('Manage Course');
  });

  it('save button is labeled "Save" when not saving', () => {
    const $ = setup(false);
    expect($.find('input').props().value).toBe('Save');
  });

  it('save button is labeled "Saving..." when saving', () => {
    const $ = setup(true);
    expect($.find('input').props().value).toBe('Saving...');
  });

});