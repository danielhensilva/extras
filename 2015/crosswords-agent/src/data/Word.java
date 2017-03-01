package data;

import java.util.ArrayList;

public class Word {

	private String word;
	
	public String getWord() {
		return word;
	}
	
	private Orientation orientation;

	public Orientation getOrientation() {
		return orientation;
	}
	
	private ArrayList<Letter> letters;
	
	public ArrayList<Letter> getLetters() {
		return this.letters;
	}
	
	private Word() {
		this.letters = new ArrayList<Letter>();
	}
	
	public Word(String word, Orientation orientation, Position position) {
		this.word = word;
		this.orientation = orientation;
		this.letters = new ArrayList<Letter>();
		
		for (char c : word.toCharArray()) {
			Letter l = new Letter(c, position);
			this.letters.add(l);
			
			position = orientation.next(position);
		}
	}
	
	public Word clone() {
		Word cloned = new Word();
		cloned.word = this.word;
		cloned.orientation = this.orientation;
		
		for (Letter l : this.letters) {
			cloned.getLetters().add(l.clone());
		}
		
		return cloned;
	}
	
	public String toString() {
		return "(" + this.word + "; " + this.orientation + ")";
	}

}