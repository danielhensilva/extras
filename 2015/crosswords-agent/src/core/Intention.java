package core;

import java.util.ArrayList;

import data.Letter;
import data.Word;
import data.WordCollection;


public class Intention {

	public WordCollection update(ArrayList<WordCollection> selectedOptions) {
		
		int bestScore = Integer.MIN_VALUE;
		WordCollection bestCollection = null;
		
		for (int i = 0; i < selectedOptions.size(); i++) {

			int currentScore = 0;
			
			for (Word word : selectedOptions.get(i).getWords()) {
				for (Word otherWord : selectedOptions.get(i).getWords()) {
			
					if (! word.equals(otherWord)) {
						
						for (Letter letter : word.getLetters()) {
							for (Letter otherLetter : otherWord.getLetters()) {

								//
								// Add score for each crossing letter.

								if (letter.getPosition().equals(otherLetter.getPosition())) {
									currentScore++;
								}

							}
						}
					}
				}
			}
			
			if (bestScore < currentScore) {
				bestScore = currentScore;
				bestCollection = selectedOptions.get(i);
			}
			
		}
		
		if (bestCollection == null && selectedOptions.size() > 0) {
			bestCollection = selectedOptions.get(0);
		}
		
		if (bestCollection != null) {
			int minX = Integer.MAX_VALUE;
			int minY = Integer.MAX_VALUE;
			
			for (Word word : bestCollection.getWords()) {
				for (Letter letter : word.getLetters()) {

					if (minX > letter.getPosition().getX()) 
						minX = letter.getPosition().getX();
					
					if (minY > letter.getPosition().getY()) 
						minY = letter.getPosition().getY();
				}
			}

			int diffX = Math.abs(minX);
			int diffY = Math.abs(minY);
			
			for (Word word : bestCollection.getWords()) {
				for (Letter letter : word.getLetters()) {
					letter.getPosition().add(diffX, diffY);
				}
			}
		}
		
		return bestCollection;
	}
	
}
