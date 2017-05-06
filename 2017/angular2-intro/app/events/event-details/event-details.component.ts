import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../shared/event.service';
import { IEvent } from '../shared/index';

@Component({
  templateUrl: '/app/events/event-details/event-details.component.html',
  styles: [`
    .container { padding: 0 20px; }
    .event-image { height: 100px; }
  `]
})
export class EventDetailsComponent {

  event: IEvent;

  constructor(
    private eventService:EventService,
    private route:ActivatedRoute) {
  }

  ngOnInit() {
    const id:number = +this.route.snapshot.params['id'];
    this.event = this.eventService.getEvent(id);
  }

}