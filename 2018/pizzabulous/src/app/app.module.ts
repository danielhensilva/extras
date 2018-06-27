import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HeaderLogoComponent } from './header/header-logo.component';
import { HeaderMenuComponent } from './header/header-menu.component';
import { HeaderAuthComponent } from './header/header-auth.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidebarMenuComponent } from './sidebar/sidebar-menu.component';
import { SidebarNotificationsComponent } from './sidebar/sidebar-notifications.component';
import { MainComponent } from './main/main.component';
import { MainBreadcrumbComponent } from './main/main-breadcrumb.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HeaderLogoComponent,
    HeaderMenuComponent,
    HeaderAuthComponent,
    SidebarComponent,
    SidebarMenuComponent,
    SidebarNotificationsComponent,
    MainComponent,
    MainBreadcrumbComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
