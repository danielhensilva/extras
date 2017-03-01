package agent.concurrent;

import core.*;
import data.*;
import java.util.ArrayList;

public class Agent extends java.util.Observable {

	private EventQueue eventQueue;

	public void enqueue(String word) {
		this.eventQueue.enqueue(word);
	}

	private Belief belief;

	private Desire desire;

	private Intention intention;

	private Thread runner;

	private WordCollection result;

	public WordCollection getResult() {
		return this.result;
	}

	public Agent() {

		//
		// initialize-state();

		this.eventQueue = new EventQueue();
		this.belief = new Belief();
		this.desire = new Desire();
		this.intention = new Intention();
	}

	public void start() {
		this.runner = new Thread(() -> {
				try {
					compute();
				} catch (Exception exc) {
					System.out.println(exc.getMessage());
				}
			}
		};
		this.runner.start();
	}

	public synchronized void compute() {

		//
		// options := option-generator(event-queue);

		String wordNotComputed = this.eventQueue.dequeue();
		if (wordNotComputed == null) {
			this.stop();
			return;
		}

		this.belief.setWordNotComputed(wordNotComputed);
		ArrayList<WordCollection> options = this.belief.generateOptions();

		if (options == null) {
			this.stop();
			return;
		}

		if (options.isEmpty()) {
			this.stop();
			return;
		}

		//
		// selected-options := deliberate(options);

		ArrayList<WordCollection> selectedOptions = this.desire.deliberate(options);

		if (selectedOptions == null) {
			this.stop();
			return;
		}

		if (selectedOptions.isEmpty()) {
			this.stop();
			return;
		}

		//
		// update-intentions(selected-options);

		// TODO refactor this.

		WordCollection bestOption = this.intention.update(selectedOptions);

		//
		// execute();

		this.setChanged();
		this.notifyObservers(bestOption);

		//
		// get-new-external-events();

		this.belief.setCurrentCollection(bestOption);

		//
		// drop-successful-attitudes();

		//
		// drop-impossible-attitudes();

	}

	public void stop() {
		this.runner.interrupt();
		this.runner = null;
	}
}
