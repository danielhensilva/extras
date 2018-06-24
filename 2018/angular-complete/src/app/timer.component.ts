import { Component } from '@angular/core';

@Component({
  selector: 'app-timer',
  template: `
    <h1>{{ minutes | number:'2.0' }}:{{ seconds | number:'2.0' }}</h1>
    <p><button (click)="togglePause()"> {{ buttonLabel }} </button></p>
  `
})
export class TimerComponent {
  minutes: number;
  seconds: number;
  isPaused: boolean;
  buttonLabel: string;

  constructor() {
    this.reset();
    setInterval(() => this.tick(), 250);
  }

  private reset() {
    this.minutes = 24;
    this.seconds = 59;
    this.buttonLabel = 'Start';
    this.isPaused = true;
  }

  tick() {
    if (this.isPaused) {
      return;
    }

    if (--this.seconds >= 0) {
      return;
    }

    this.seconds = 59;

    if (--this.minutes >= 0) {
      return;
    }

    this.reset();
  }

  togglePause() {
    this.isPaused = !this.isPaused;
    this.buttonLabel = this.isPaused ? 'Resume' : 'Pause';
  }
}
