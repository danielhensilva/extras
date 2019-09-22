import expect from 'expect';
import * as selectors from './selectors';

describe('Author Selectors', () => {
  
  describe('selectAuthors', () => {
    it('should return author data formatted fro use in a dropdown', () => {
      const authors = [
        {id:'cory-house', firstName: 'Cory', lastName: 'House'},
        {id:'scott-allen', firstName: 'Scott', lastName: 'Allen'}
      ];
      const expected = [
        {value: 'cory-house', text: 'HOUSE, Cory'},
        {value: 'scott-allen', text: 'ALLEN, Scott'}
      ];
      expect(selectors.selectAuthors(authors)).toEqual(expected);
    })
  });

});