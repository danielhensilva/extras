import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { EliteApiProvider } from "../../providers/elite-api/elite-api";
import { GamePage } from "../game/game";

import * as _ from 'lodash';

@Component({
  selector: 'page-team-detail',
  templateUrl: 'team-detail.html',
})
export class TeamDetailPage {

  public team: any = {};
  public games: any[];
  private tourneyData: any;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    private api: EliteApiProvider) {
  }

  ionViewDidLoad() {
    this.team = this.navParams.data;
    this.tourneyData = this.api.getCurrentTourney();
    this.games = _
      .chain(this.tourneyData.games)
      .filter(g => g.team1Id === this.team.id ||
                   g.team2Id === this.team.id)
      .map(g => {
        const isTeam1 = (g.team1Id == this.team.id);
        const opponentName = isTeam1 ? g.team2 : g.team1;
        const scoreDisplay = TeamDetailPage.getScoreDisplay(isTeam1, g.team1Score, g.team2Score);
        return {
          gameId: g.id,
          opponent: opponentName,
          time: Date.parse(g.time),
          location: g.location,
          locationUIrl: g.locationUrl,
          scoreDisplay: scoreDisplay,
          homeAway: (isTeam1 ? "vs." : "at")
        };
      })
      .value();
  }

  private static getScoreDisplay(isTeam1: boolean, team1Score, team2Score) {
    if (team1Score && team2Score) {
      const teamScore = (isTeam1 ? team1Score : team2Score);
      const opponentScore = (isTeam1 ? team2Score : team1Score);
      const winIndicator = teamScore > opponentScore ? 'W: ' : 'L: ';
      return winIndicator + teamScore + '-' + opponentScore;
    }
    return '';
  }

  gameClicked($event, game: any) {
    const sourceGame = this.tourneyData.games.find(g => g.id === game.gameId);
    this.navCtrl.parent.parent.pish(GamePage, sourceGame);
  }
}
