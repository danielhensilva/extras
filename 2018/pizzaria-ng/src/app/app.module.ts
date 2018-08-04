import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ToggleShowDirective } from './directives/toggle-show.directive';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ToggleShowDirective
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
