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
import { SidebarUserComponent } from './sidebar/sidebar-user.component';
import { AuthSignInComponent } from './auth/auth-sign-in.component';
import { AuthSignUpComponent } from './auth/auth-sign-up.component';
import { AuthComponent } from './auth/auth.component';

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
    MainBreadcrumbComponent,
    SidebarUserComponent,
    AuthSignInComponent,
    AuthSignUpComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
