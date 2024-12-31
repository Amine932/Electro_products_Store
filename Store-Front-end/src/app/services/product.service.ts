import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from "@angular/common/http";
import {Product} from "../../../Models/Product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  getAllProducts(){
    return this.http.get<Array<Product>>("http://localhost:8087/api/Products");
  }
  updateProduct(id:number,product:any){
    return this.http.put(`http://localhost:8087/api/Products/${id}`,product);
  }
  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8087/api/Products/${id}`);
  }

}
