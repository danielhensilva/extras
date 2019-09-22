import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';

import {
  EventsListComponent,
  EventThumbnailComponent,
  EventDetailsComponent,
  CreateEventComponent,
  EventListResolver,
  EventRouteActivator,
  EventService
} from './events/index';


import {EventsAppComponent} from './events-app.component';
import {NavBarComponent} from './nav/navbar.component';
import {ToastrService} from './common/toastr.service';
import {Error404Component} from './errors/404.component';
import {AuthService} from './user/auth.service';
import {appRoutes} from './routes';

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [
    CreateEventComponent,
    EventsAppComponent,
    EventsListComponent,
    EventThumbnailComponent,
    EventDetailsComponent,
    NavBarComponent,
    Error404Component
  ],
  providers: [
    EventService,
    ToastrService,
    EventRouteActivator,
    EventListResolver,
    AuthService,
    { provide: 'canDeactivateCreateEvent', useValue: checkDirtyState }
  ],
  bootstrap: [EventsAppComponent]
})
export class AppModule {
}

function checkDirtyState(component: CreateEventComponent) {
  if (component.isDirty) {
    return confirm('You have not saved this event. Do really want to cancel?');
  }
  return true;
}
