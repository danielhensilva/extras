import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { MapPage, TeamHomePage } from '../pages';
import { EliteApi } from '../../shared/shared';
declare var window: any;

@Component({
  templateUrl: 'game.page.html',
})
export class GamePage {
  game: any;

  constructor(
    public nav: NavController,
    public navParams: NavParams,
    public eliteApi: EliteApi) { }

  ionViewDidLoad(){
    this.game = this.navParams.data;
    this.game.gameTime = Date.parse(this.game.time);
  }

  teamTapped(teamId){
    let tourneyData = this.eliteApi.getCurrentTourney();
    let team = tourneyData.teams.find(t => t.id === teamId);
    this.nav.push(TeamHomePage, team); 
  }

  goToDirections(){
    let tourneyData = this.eliteApi.getCurrentTourney();
    let location = tourneyData.locations[this.game.locationId];
    window.location = `geo:${location.latitude},${location.longitude};u=35;`;
  }

  goToMap(){
    this.nav.push(MapPage, this.game);
  }

  isWinner(score1, score2){
    //return Number(score1) > Number(score2);
    return Number(score1) > Number(score2) ? 'secondary' : '';
  }
}
