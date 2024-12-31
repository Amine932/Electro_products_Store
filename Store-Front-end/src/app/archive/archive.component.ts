import {APP_INITIALIZER, Component, OnInit} from '@angular/core';
import {Sale} from "../../../Models/Sale";
import {Product} from "../../../Models/Product";
import {Client} from "../../../Models/Client";
import {SaleService} from "../services/sale.service";
import {ProductService} from "../services/product.service";
import {ClientService} from "../services/client.service";
import {Router} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, NgForm} from "@angular/forms";
import {Location, NgForOf, NgIf} from "@angular/common";
import {map, switchMap} from "rxjs";
import {KeycloakService} from "keycloak-angular";
import {SwalService} from "../services/swal.service";






@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrl: './archive.component.css'
})
export class ArchiveComponent implements OnInit {
  archive: Array<Sale> = [];
  products: Array<Product> = [];
  nl_products: Array<Product> = [];
  clients: Array<Client> = [];
  selectedSale!: Sale;
  test = { year: '', month: '', productQuantity: '', idClient: '', idProduct: '' };

  constructor(
    private http: HttpClient,
    public router: Router,
    public location: Location,
    private saleService: SaleService,
    private productService: ProductService,
    private clientService: ClientService,
    private swalService: SwalService,
  ) {}


  ngOnInit(): void {
    this.getArchive();
    this.getProducts();
    this.getClients();

    // @ts-ignore
    window.component=this;

  }

  getArchive() {
    this.saleService.getAllSales().subscribe({
      next: (value) => {
        this.archive = value;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  getProducts() {
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

  getClients() {
    this.clientService.getAllClient().subscribe({
      next: (value) => {

        this.clients = value;

      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  async deleteArchive(sale: Sale) {
    const result = await this.swalService.confirm("sale");

    if (result.isConfirmed) {
      const productToUpdate =sale.product;

      if (productToUpdate) {
        this.saleService.deleteSale(sale.id).pipe(
          switchMap(() => {
            productToUpdate.quantity += sale.productQuantity;
            return this.productService.updateProduct(productToUpdate.id, productToUpdate);
          }),
          switchMap(() => {
            return this.saleService.getAllSales();
          })
        ).subscribe({
          next: (value) => {
            this.archive = value;

            this.swalService.success("Sale deleted successfully and sales updated.");
            console.log("Sale deleted successfully and sales updated.");
          },
          error: (err) => {
            this.swalService.error("Error handling delete operation");
            console.log("Error handling delete operation:", err);
          }
        });
      } else {
        this.swalService.error("Product associated with the sale not found.");
        console.log("Product associated with the sale not found.");
      }
    }
  }

  updateArchive(upd: NgForm) {
    const updatedValues = upd.value;

    const updatedSale = {
      year: updatedValues.year || this.selectedSale.year,
      month: updatedValues.month || this.selectedSale.month,
      productQuantity: updatedValues.productQuantity || this.selectedSale.productQuantity,
      idClient: updatedValues.idClient || this.selectedSale.idClient,
      idProduct: updatedValues.idProduct || this.selectedSale.idProduct
    };

    this.saleService.updateSale(this.selectedSale.id, updatedSale)
      .pipe(switchMap(() => {
        return this.saleService.getAllSales();
      }))
      .subscribe({
        next: (value) => {
          this.archive=value;
          this.swalService.success("Sale updated successfully");
        },
        error: (err) => {
          console.log("Error updating sale:", err);
          this.swalService.error("Failed to update Sale. Please try again.");
        }
      });
  }

  selectSale(sale: Sale) {
    this.selectedSale = sale;
  }




}
