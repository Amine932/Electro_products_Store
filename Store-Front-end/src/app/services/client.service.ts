import { Injectable } from '@angular/core';
import {HttpClient,HttpClientModule} from "@angular/common/http";
import {Client} from "../../../Models/Client";
import {Observable} from "rxjs";
import {Product} from "../../../Models/Product";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http:HttpClient) { }

  getAllClient() {
    return this.http.get<Array<Client>>("http://localhost:8086/api/Clients");
  }

  updateClient(id: number, client: any) {
    return this.http.put<any>(`http://localhost:8086/api/Clients/${id}`, client);
  }

  postClient(client: any) {
    return this.http.post<Client>("http://localhost:8086/api/Clients", client);
  }

  deleteClient(id: number) {
    return this.http.delete(`http://localhost:8086/api/Clients/${id}`);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`http://localhost:8086/api/Clients/${id}`);
  }


}
