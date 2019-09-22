import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { /*MyTeamsPage,*/ StandingsPage, TeamDetailPage } from '../pages';

@Component({
  templateUrl: 'team-home.page.html',
})
export class TeamHomePage {
  team: any;
  teamDetailTab = TeamDetailPage;
  standingsTab = StandingsPage;

  constructor(public nav: NavController, public navParams: NavParams) {
    this.team =  this.navParams.data; 
  }

  goHome(){
    //this.nav.push(MyTeamsPage);
    this.nav.popToRoot();
  }
}
