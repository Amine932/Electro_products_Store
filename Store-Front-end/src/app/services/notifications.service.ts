import {EventEmitter, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notifications} from "../../../Models/Notifications";

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  private apiUrl = 'http://localhost:8040/api/';

  constructor(private httpClient: HttpClient) { }

  getNotifications(): Observable<Notifications[]> {
    return this.httpClient.get<Notifications[]>(this.apiUrl + 'notifications');
  }

  getLastNotification(): Observable<Notifications> {
    return this.httpClient.get<Notifications>(this.apiUrl + 'last-notification');
  }
}
