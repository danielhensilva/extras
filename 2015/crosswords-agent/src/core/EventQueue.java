package core;

import java.util.ArrayList;

public class EventQueue {

	private ArrayList<String> wordsNotComputed;
		
	public EventQueue() {
		this.wordsNotComputed = new ArrayList<String>();
	}
	
	public void enqueue(String word) {
		this.wordsNotComputed.add(word);
	}
	
	public String dequeue() {
		if (this.isEmpty()) {
			return null;
		}
		return this.wordsNotComputed.remove(0);
	}
	
	public boolean isEmpty() {
		return this.wordsNotComputed.isEmpty();
	}
	
}
