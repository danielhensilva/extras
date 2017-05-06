import { Component, Input, Output, EventEmitter } from '@angular/core';
import { IEvent } from './shared/index';

@Component({
  selector: 'event-thumbnail',
  templateUrl: 'app/events/event-thumbnail.component.html',
  styleUrls: ['app/events/event-thumbnail.component.css'] 
})
export class EventThumbnailComponent {
  @Input() event: IEvent
  @Output() eventClick = new EventEmitter();

  someProperty: string = 'some value';

  handleClickMe() {
    const eventId = this.event.id;
    this.eventClick.emit(eventId);
  }

  logFoo() {
    console.log('foo');
  }
}