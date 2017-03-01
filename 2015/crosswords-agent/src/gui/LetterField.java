package gui;

import data.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LetterField extends JTextField {
	
	private static final long serialVersionUID = -4056585714167535128L;

	private static final int width = 18;
	private static final int height = 18;
	
	private static final int spacing = 20;
	private static final int margin = 5;

	private static final Color emptyText = Color.WHITE;
	private static final Color matchedText = Color.GREEN;
	private static final Color notMatchedText = Color.RED;
	
	private String letter;
	private Position position;
		
	public LetterField(String letter, Position position) {
		this.letter = letter;
		this.prepareKeyListener();

		this.position = position;
		this.prepareBounds();
		
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private void prepareBounds() {
		int x = this.position.getX() * LetterField.spacing + LetterField.margin;
		int y = this.position.getY() * LetterField.spacing + LetterField.margin;
		this.setBounds(x, y, width, height);
	}
	
	private void prepareKeyListener() {
		this.addKeyListener(
			new KeyListener() {
				public void keyReleased(KeyEvent e) { 
					calculateAndApplyBackgroundColor();
				}
				public void keyTyped(KeyEvent e) { }
				public void keyPressed(KeyEvent e) { }
		});
	}
	
	private void calculateAndApplyBackgroundColor() {
		Color background = this.calculateBackgroundColor();
		this.setBackground(background);
	}
	
	private Color calculateBackgroundColor() {
		String current = this.getCurrentText();
		if (current.equals("")) {
			return LetterField.emptyText;
		}
		
		String expected = this.getExpectedText();
		if (current.equals(expected)) {
			return LetterField.matchedText;
		}
		
		return LetterField.notMatchedText;
	}
	
	private String getCurrentText() {
		return this.getText().toLowerCase();
	}
	
	private String getExpectedText() {
		return this.letter.toLowerCase();
	}
}
