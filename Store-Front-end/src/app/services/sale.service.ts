import { Injectable } from '@angular/core';
import {HttpClient,HttpClientModule} from "@angular/common/http";
import {Sale} from "../../../Models/Sale";

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  constructor(private http:HttpClient) { }

  getAllSales(){
    return  this.http.get<Array<Sale>>("http://localhost:8083/api/Sales");
  }
  deleteSale(id:number){
    return this.http.delete(`http://localhost:8083/api/Sales/${id}`);
  }
  updateSale(id:number,sale:any){
    return this.http.put<any>(`http://localhost:8083/api/Sales/${id}`,sale);
  }
  postSale(sale:any){
    return this.http.post("http://localhost:8083/api/Sales",sale);
  }
}
