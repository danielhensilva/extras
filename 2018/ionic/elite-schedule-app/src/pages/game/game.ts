import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { EliteApiProvider } from "../../providers/elite-api/elite-api";
import { TeamHomePage } from "../team-home/team-home";

@Component({
  selector: 'page-game',
  templateUrl: 'game.html',
})
export class GamePage {
  public game: any = {};

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    private api: EliteApiProvider) {
  }

  ionViewDidLoad() {
    this.game = this.navParams.data;
  }

  teamTapped(teamId) {
    const tourneyData = this.api.getCurrentTourney();
    const team = tourneyData.teams.find(t => t.id === teamId);
    this.navCtrl.push(TeamHomePage, team);
  }
}
