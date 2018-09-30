import { Component } from '@angular/core';
import { IonicPage, LoadingController, NavController } from 'ionic-angular';
import { TeamsPage } from "../teams/teams";
import { EliteApiProvider } from "../../providers/elite-api/elite-api";

@IonicPage()
@Component({
  selector: 'page-tournaments',
  templateUrl: 'tournaments.html',
})
export class TournamentsPage {

  public tournaments: any;

  constructor(
    public navCtrl: NavController,
    private api: EliteApiProvider,
    public loadingController: LoadingController) {
  }

  ionViewDidLoad() {
    this.tournaments = [];

    let loader = this.loadingController.create({
      content: 'Getting tournaments...',
      spinner: 'dots',
    });

    loader.present().then(() => {
      this.api.getTournaments().subscribe(response => {
        this.tournaments = response;
        loader.dismissAll();
      });
    });
  }

  itemTapped($event, tourney) {
    this.navCtrl.push(TeamsPage, tourney);
  }

}
