import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { TeamHomePage } from "../team-home/team-home";
import { EliteApiProvider } from "../../providers/elite-api/elite-api";

@Component({
  selector: 'page-teams',
  templateUrl: 'teams.html',
})
export class TeamsPage {

  private teams: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private api: EliteApiProvider) {
  }

  ionViewDidLoad() {
    const tourneyId = this.navParams.data.id;
    this.api.getTournamentDetails(tourneyId).subscribe(data => this.teams = data.teams)
  }

  itemTapped($event, team) {
    this.navCtrl.push(TeamHomePage, team);
  }

}
