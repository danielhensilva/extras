package data;

public class Letter {
	
	private char character;
	
	public String getCharacter() {
		return this.character + "";
	}
	
	private Position position;

	public Position getPosition() {
		return this.position;
	}
	
	public Letter(char character) {
		this(character, new Position());
	}
	
	public Letter(char character, Position position) {
		this.character = character;
		this.position = position;
	}
	
	public Letter clone() {
		return new Letter(this.character, this.position);
	}
	
	public String toString() {
		return "(" + this.character + "; " + this.position + ")"; 
	}
}
