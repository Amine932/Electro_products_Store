import {Product} from "./Product";
import {Client} from "./Client";

export interface Sale{

  id:number,
  year:string,
  month:string,
  idProduct:number,
  idClient:number,
  productQuantity:number,
  product:Product
  client:Client

}
