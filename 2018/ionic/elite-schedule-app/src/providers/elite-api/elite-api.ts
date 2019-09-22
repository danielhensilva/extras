import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { of } from "rxjs/observable/of";

@Injectable()
export class EliteApiProvider {

  private baseUrl = 'http://localhost:3000';
  private currentTourney: any = {};

  constructor(private http: HttpClient) {
  }

  getTournaments(): Observable<any> {
    return this.http.get(`${this.baseUrl}/tournaments`);
  }

  getTournamentDetails(tourneyId): Observable<any> {
    return new Observable<any>(observer => {

      const success = tourney => {
        this.currentTourney = tourney;
        observer.next(tourney);
      };

      const error = message => {
        observer.error(message);
      };

      this.http
        .get(`${this.baseUrl}/tournaments-data/${tourneyId}`)
        .subscribe(success, error);
    });
  }

  getCurrentTourney() {
    return this.currentTourney;
  }
}
