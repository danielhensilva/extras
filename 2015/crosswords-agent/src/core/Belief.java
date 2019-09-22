package core;

import java.util.ArrayList;
import data.Letter;
import data.Orientation;
import data.Position;
import data.Word;
import data.WordCollection;


public class Belief {

	private String wordNotComputed;
	
	public String getWordNotComputed() {
		return this.wordNotComputed;
	}
	
	public void setWordNotComputed(String word) {
		this.wordNotComputed = word;
	}
	
	private WordCollection currentCollection;
	
	public WordCollection getCurrentCollection() {
		return this.currentCollection;
	}
	
	public void setCurrentCollection(WordCollection currentCollection) {
		this.currentCollection = currentCollection;
	}
	
	public Belief() {
	}
	
	public ArrayList<WordCollection> generateOptions() {
		
		String wordNotComputed = getNextWordNotComputed();

		// No word to work on.
		if (wordNotComputed == null) {
			return null;
		}
		
		ArrayList<WordCollection> options = this.createEmptyOptions();
		
		// There is no word in for current board.
		if (this.getCurrentCollection().getWords().isEmpty()) {
			this.generateDefaultOptions(options, wordNotComputed);
			return options;
		}
		
		// Generates all possible options.
		int length = options.size();
		for (int i = 0; i < length; i++) {
			for (Word computedWord : options.get(i).getWords()) {
				this.generateOptionsPerWord(options, wordNotComputed, computedWord);
			}
		}
		
		return options;
	}

	private String getNextWordNotComputed() {
		String wordNotComputed = this.getWordNotComputed();

		if (wordNotComputed == null) {
			return null;
		}
		
		this.setWordNotComputed(null);
		return wordNotComputed;
	}
		
	private ArrayList<WordCollection> createEmptyOptions() {
		ArrayList<WordCollection> options = new ArrayList<WordCollection>();
		
		if (this.getCurrentCollection() == null) {
			WordCollection words = new WordCollection();
			this.setCurrentCollection(words);
		}

		WordCollection computedWordCollection = this.getCurrentCollection().clone();
		options.add(computedWordCollection);
		
		return options;
	}
	
	private void generateDefaultOptions(ArrayList<WordCollection> options, String wordNotComputed) {
		this.addOption(options, wordNotComputed, Orientation.HORIZONTAL);
		this.addOption(options, wordNotComputed, Orientation.VERTICAL);
	}
	
	private void generateOptionsPerWord(ArrayList<WordCollection> options, String wordNotComputed, Word wordComputed) {
		for (Letter letter : wordComputed.getLetters()) {
				
			int index = wordNotComputed.indexOf(letter.getCharacter());
			
			while(index >= 0) {
				
				Orientation wordNotComputedOrientation = wordComputed.getOrientation().getInverse();			
				Position wordNotComputedPosition = wordNotComputedOrientation.previous(letter.getPosition(), index);
				this.addOption(options, wordNotComputed, wordNotComputedOrientation, wordNotComputedPosition);
				
				index = wordNotComputed.indexOf(letter.getCharacter(), ++index);
			}
		}
	}

	private void addOption(ArrayList<WordCollection> options, String wordNotComputed, Orientation orientation) {
		this.addOption(options, wordNotComputed, orientation, new Position(0, 0));
	}
	
	private void addOption(ArrayList<WordCollection> options, String wordNotComputed, Orientation orientation, Position position) {
		Word word = new Word(wordNotComputed, orientation, position);
		
		WordCollection collection = this.getCurrentCollection().clone();
		collection.add(word);
		
		options.add(collection);
	}
		
}
