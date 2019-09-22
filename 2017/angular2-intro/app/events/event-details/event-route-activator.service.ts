import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { EventService } from '../shared/event.service';

@Injectable()
export class EventRouteActivator implements CanActivate {

  constructor(private eventService:EventService, private router:Router) {
  }

  canActivate(route:ActivatedRouteSnapshot) {
    const id:number = +route.params['id'];
    const eventExists:boolean = !!this.eventService.getEvent(id);
    
    if (eventExists)
      return true;

    this.router.navigate(['/404']);
    return false;
  }

}