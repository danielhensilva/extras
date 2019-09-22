package gui;

import data.*;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Render {

	private JPanel panel;
	
	private WordCollection words;
	
	private ArrayList<Position> occupiedPositions;
	
	public Render(JPanel panel, WordCollection words) {
		this.occupiedPositions = new ArrayList<>();
		this.panel = panel;
		this.words = words;
	}
	
	public void execute() {
		this.clearPanel();
		this.renderWords();
		this.refreshPanel();
	}	
	
	private void clearPanel() {
		this.panel.removeAll();
		this.occupiedPositions = new ArrayList<>();
	}
	
	private void refreshPanel() {
		this.panel.updateUI();
	}
	
	private void renderWords() {
		for(Word word : this.words.getWords()) {
			this.renderWord(word);
		}
	}
	
	private void renderWord(Word word) {
		for (Letter letter : word.getLetters()) {
			renderLetter(letter);
		}
	}
	
	private void renderLetter(Letter letter) {
		String character = letter.getCharacter();
		Position position = letter.getPosition();
		
		if (this.isOccupied(position)) {
			return;
		}
		
		this.renderField(character, position);
	}
	
	private boolean isOccupied(Position position) {
		for(Position p : this.occupiedPositions) {
			if (position.equals(p)) { 
				return true;
			}
		}
		
		return false;
	}
	
	private void renderField(String letter, Position position) {
		Component field = this.createField(letter, position);
		this.renderField(field);
	}

	private Component createField(String letter, Position position) {
		this.occupiedPositions.add(position);
		return new LetterField(letter, position);
	}
	
	private void renderField(Component field) {
		this.panel.add(field);
	}
}
