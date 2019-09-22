import { Component, OnInit } from '@angular/core';
import { EventService } from './shared/event.service';
import { ToastrService } from '../common/toastr.service';
import { ActivatedRoute } from '@angular/router';
import { IEvent } from './shared/index';

@Component({
  templateUrl: 'app/events/events-list.component.html'
})
export class EventsListComponent implements OnInit {
  events: IEvent[];

  constructor(
      private eventService: EventService,
      private toastrService: ToastrService,
      private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.events = this.route.snapshot.data['events'];
  }

  handleEventClicked(eventId) {
    console.log('received:' + eventId);
  }

  handleThumbnailClick(eventName) {
    this.toastrService.success(eventName);
  }
}
