import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";

@Injectable()
export class EliteApiProvider {

  private baseUrl = 'http://localhost:3000';

  constructor(private http: HttpClient) {
  }

  getTournaments(): Observable<any> {
    return this.http.get(`${this.baseUrl}/tournaments`);
  }

  getTournamentDetails(tourneyId): Observable<any> {
    return this.http.get(`${this.baseUrl}/tournaments-data/${tourneyId}`);
  }

}
