package main;

import java.util.Observable;
import java.util.Observer;

import data.WordCollection;
import agent.concurrent.Agent;
import gui.Render;
import gui.Screen;

public class Main {
	
	private final static Screen screen = new Screen();
	private final static Agent agentOne = new Agent();
	private final static Agent agentTwo = new Agent();
	
	private final static Observer screenObserver = new Observer() {
		public void update(Observable o, Object arg) {
			String newWord = (String)arg;
			
			agentOne.enqueue(newWord);
			agentOne.start();
			
			agentTwo.enqueue(newWord);
			agentTwo.start();
		}
	};
	
	private final static Observer agentOneObserver = new Observer() {
		public void update(Observable o, Object arg) {
			WordCollection words = (WordCollection)arg;
			new Render(screen.getPanelOne(), words).execute();
		}
	};
	
	private final static Observer agentTwoObserver = new Observer() {
		public void update(Observable o, Object arg) {
			WordCollection words = (WordCollection)arg;
			new Render(screen.getPanelTwo(), words).execute();
		}
	};
	
	public static void main(String[] args) {
		screen.addObserver(screenObserver);		
		agentOne.addObserver(agentOneObserver);
		agentTwo.addObserver(agentTwoObserver);		
	}	
}
