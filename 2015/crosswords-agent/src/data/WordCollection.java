package data;

import java.util.ArrayList;

import data.Word;


public class WordCollection {

	private ArrayList<Word> words;
	
	public ArrayList<Word> getWords() {
		return this.words;
	}
	
	public WordCollection() {
		this.words = new ArrayList<Word>();
	}
	
	public void add(Word word) {
		this.words.add(word);
	}
	
	public void remove(Word word) {
		this.words.remove(word);
	}
	
	public WordCollection clone() {
		WordCollection cloned = new WordCollection();
		for (Word word : this.words) {
			cloned.words.add(word.clone());
		}
		return cloned;
	}
	
	public String toString() {
		String value = "";
		for(Word word : this.words) {
			value = value.concat(word.toString());
		}		
		return value;
	}
}
