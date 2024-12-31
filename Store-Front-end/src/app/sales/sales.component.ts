import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Product} from "../../../Models/Product";
import {Location, NgForOf} from "@angular/common";
import {FormsModule, NgForm} from "@angular/forms";
import {Client} from "../../../Models/Client";
import {Router} from "@angular/router";
import {SaleService} from "../services/sale.service";
import {ProductService} from "../services/product.service";
import {ClientService} from "../services/client.service";
import {map, switchMap} from "rxjs";
import {Sale} from "../../../Models/Sale";
import { NgToastService } from "ng-angular-popup";
import {NotificationsService} from "../services/notifications.service";
import {Notifications} from "../../../Models/Notifications";

import Swal from 'sweetalert2';

@Component({
  selector: 'app-sale',
  templateUrl: './sales.component.html',
  styleUrl: './sales.component.css'
})
export class SalesComponent implements OnInit{
  products: Array<Product> = [];
  client: Array<Client> = [];
  archive : Array<Sale> = [];
  nl_products: Array<Product> = [];
  sale : Array<Sale>=[];
  notification : Notifications = {
    notificationId: 0,
    desc:"",
    clientId:0,
    productId:0

  }
  test = {
    secondName: "",
    name: "",
    city: "",
    year: "",
    month: "",
    productQuantity: "",
    idClient: "",
    idProduct: ""
  };

  constructor(
    private http: HttpClient,
    public router: Router,
    public location: Location,
    private saleService: SaleService,
    private productService: ProductService,
    private clientService: ClientService,
    private notificationService : NotificationsService,
    private toast : NgToastService
  ) {}

  ngOnInit(): void {
    this.getProduct();
    this.getClient();

  }

  getProduct() {
    this.productService.getAllProducts().pipe(
      map((products) => products.filter((p) => p.quantity > 0))
    ).subscribe({
      next: (filteredProducts) => {
        this.nl_products = filteredProducts;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getClient() {
    this.clientService.getAllClient().subscribe({
      next: (value) => { this.client = value; },
      error: (err) => { console.log(err); }
    });
  }
  getLastNotification() {
    this.notificationService.getLastNotification().subscribe({
      next: (value) => {
        if (!value)
          return;
        this.notification = value;
        const [clientName] = this.client.filter(cl=> cl.id === value.clientId)
        const [productName] = this.products.filter(p=> p.id === value.productId)
        this.toast.success({
          detail: "new Sale Added",
          summary: `Client: ${clientName.secondName} ${clientName.name} \n Product: ${productName.brand}`,
          duration: 100000
        });
        },
      error: (err) => { console.log(err); }
    });
  }


  postSale(saleForm: NgForm) {
    let valeur = saleForm.value;
    this.productService.getProductById(valeur.idProduct).subscribe({
      next: (product) => {
        if (product && valeur.productQuantity <= product.quantity) {
          let newSale = {
            name: valeur.name,
            secondName: valeur.secondName,
            city: valeur.city,
            year: valeur.year,
            month: valeur.month,
            productQuantity: valeur.productQuantity,
            idClient: valeur.idClient,
            idProduct: valeur.idProduct
          };

          this.saleService.postSale(newSale).pipe(
            switchMap(() => {
              return this.saleService.getAllSales();
            })
          ).subscribe({
            next: (value) => {
              this.sale = value;
              Swal.fire({
                title: "Success",
                text: "Sale added successfully!",
                icon: "success"
              });
              this.getLastNotification()

          },
            error: (err) => {
              console.log("Error adding sale:", err);
              Swal.fire({
                title: "Oops...",
                text: "Failed to add sale. Please try again.",
                icon: "error"
              });
            }
          });
        } else {
          Swal.fire({
            title: "Warning",
            text: "Invalid quantity or product not found.",
            icon: "warning"
          });
        }
      },
      error: (err) => {
        console.log("Error fetching product:", err);
        Swal.fire({
          title: "Oops...",
          text: "Failed to fetch product details. Please try again.",
          icon: "error"
        });
      }
    });
  }

  updateProductOnSale(sale: NgForm) {
    let valeur = sale.value;
    this
      .productService
      .getProductById(valeur.idProduct)
      .subscribe({
        next: (product) => {
          if (product) {
              if(product.quantity<valeur.productQuantity){
                return;
              }
            product.quantity -= valeur.productQuantity;

            this.productService.updateProduct(product.id, product).pipe(
              switchMap(() => {
                return this.productService.getAllProducts();
              })).subscribe({
              next: (value) => {
                this.products=value;

              },
              error: (err) => {
                console.log('Error updating product quantity:', err);
              }
            });
          } else {
            console.log('Product not found.');
          }
        },
        error: (err) => {
          console.log('Error fetching product:', err);
        }
      });
  }
  updateClientOnSale(sale: NgForm) {
    let valeur = sale.value;
    this.clientService.getClientById(valeur.idClient).subscribe({
      next: (client) => {
        if (client) {
          client.nbr_sales++;

          this.clientService.updateClient(client.id, client).pipe(
            switchMap(() => {
              return this.clientService.getAllClient();
            })).subscribe({
            next: (value) => {
              this.client=value;
              Swal.fire({
                title: "Success",
                text: "Client updated successfully",
                icon: "success"
              });
            },
            error: (err) => {
              console.log('Error updating client:', err);
              alert('Failed to update client. Please try again.');
            }
          });
        } else {
          console.log('Client not found.');
        }
      },
      error: (err) => {
        console.log('Error fetching client:', err);
      }
    });
  }


}


