package core;

import java.util.ArrayList;

import data.Letter;
import data.Word;
import data.WordCollection;


public class Desire {
	
	public Desire() {
	}

	public ArrayList<WordCollection> deliberate(ArrayList<WordCollection> options) {
		ArrayList<WordCollection> selectedOptions = new ArrayList<WordCollection>(options);
		
		option: 
		for (int i = 0; i < selectedOptions.size(); i++) {
			
			//
			// Remove options with no words.
			
			if (selectedOptions.get(0).getWords().size() == 0) {
				selectedOptions.remove(i--);
				continue option;
			}

			for (Word word : selectedOptions.get(i).getWords()) {
				for (Word otherWord : selectedOptions.get(i).getWords()) {
			
					if (! word.equals(otherWord)) {
						
						for (Letter letter : word.getLetters()) {
							for (Letter otherLetter : otherWord.getLetters()) {
								
								if (letter.getPosition().equals(otherLetter.getPosition())) {

									//
									// Remove crossing words in the same orientation.
									
									if (word.getOrientation().equals(otherWord.getOrientation())) {
										selectedOptions.remove(i--);
										continue option;
									}
									
									//
									// Remove distinct letter in same position.
									
									if (! letter.getCharacter().equals(otherLetter.getCharacter())) {
										selectedOptions.remove(i--);
										continue option;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return selectedOptions;
	}
}
