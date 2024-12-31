import {Component, OnInit} from '@angular/core';
import {NgForOf, SlicePipe} from "@angular/common";
import {Product} from "../../../Models/Product";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ProductService} from "../services/product.service";

@Component({
  selector: 'app-roms',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{


  products : Array<Product> = [];

  l_products : Array<Product> = [];

  nl_products : Array<Product> =[] ;
  constructor(private http: HttpClient,private productService:ProductService) {}
  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.productService.getAllProducts().subscribe({
      next: value => {
        this.products = value;
        for (let p of this.products) {
          console.log("Product:", p); // Vérifier chaque produit reçu
          if (p.quantity < 20) {
            this.l_products.push(p);
          } else {
            this.nl_products.push(p);
          }
        }
        console.log("Limited Products:", this.l_products); // Vérifier les produits limités
        console.log("Unlimited Products:", this.nl_products); // Vérifier les produits illimités
      },
      error: err => {
        console.log(err);
      }
    });
  }

  protected readonly Math = Math;

  chunkArray(array: Array<any>, chunkSize: number): Array<Array<any>> {
    const chunks = [];
    for (let i = 0; i < array.length; i += chunkSize) {
      chunks.push(array.slice(i, i + chunkSize));
    }
    return chunks;
  }
}

