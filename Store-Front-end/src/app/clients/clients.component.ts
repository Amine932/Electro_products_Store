import {Component, OnInit} from '@angular/core';
import {Location, NgForOf} from "@angular/common";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Client} from "../../../Models/Client";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {Sale} from "../../../Models/Sale";
import {Router} from '@angular/router';
import {ClientService} from "../services/client.service";
import {SaleService} from "../services/sale.service";
import {of, switchMap} from "rxjs";
import {SwalService} from "../services/swal.service";


@Component({
  selector: 'app-client',
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientComponent implements OnInit{
  archive : Array<Sale> = [];
  cl!:Client;

  client : Array<Client>=[];
  test: any = {
    name: '',
    secondName: '',
    city: '',
    phoneNumber: ''
  };
  constructor(
    private swalService: SwalService,
    private http: HttpClient,
    public router:Router,
    public location:Location,
    private clientService:ClientService,
    private saleService:SaleService,
    ){}
  ngOnInit(): void {
    // @ts-ignore
    window.component = this;
    this.getClient();
    this.getArchive();
    this.updateSalesCountForClients();
  }

  getClient(){
    this.clientService.getAllClient()
      .subscribe({
      next:value => {this.client=value},error:err => {console.log(err)}
    });
  }

  updateClient(upd:NgForm){

    const updatedValues = upd.value;

    const updatedClient = {
      name: updatedValues.name || this.cl.name,
      secondName: updatedValues.secondName || this.cl.secondName,
      city: updatedValues.city || this.cl.city,
      phoneNumber: updatedValues.phoneNumber || this.cl.phoneNumber
    };
    this.clientService.updateClient(this.cl.id,updatedClient)
      .pipe(switchMap(() => {
        return this.clientService.getAllClient();
      }))
      .subscribe({
        next: (value) => {
          this.client = value;
          this.swalService.success("Client updated successfully");
        },
        error: (err) => {
          console.log("Error updating client:", err);
          this.swalService.error("Failed to update client. Please try again.");
        }
      });
  }

  postClient(add: NgForm) {
    let valeur = add.value;
    let newClient = {
      name: valeur.name,
      secondName: valeur.secondName,
      city: valeur.city,
      phoneNumber: valeur.phoneNumber
    };

    this.clientService.postClient(newClient).pipe(
      switchMap(() => {
        return this.clientService.getAllClient();
      }))
      .subscribe({
      next: (value) => {
        this.client = value;
        this.swalService.success("Client added successfully");
        },
      error: (err) => {
        console.log("Error adding client:", err);
        this.swalService.error("Failed to add client. Please try again.");
      }
    });
  }

  async deleteClient(client_delete: Client) {
    const result = await this.swalService.confirm("client");

    if (!result.isConfirmed)
      return;

    const clientID = client_delete?.id; // Safely accessing the client ID

    if (clientID === undefined || clientID === null) {
      this.swalService.error("Invalid client ID.");
      return;
    }

    const hasSale = this.archive.some(sale => sale.client?.id === clientID);

    if (hasSale) {
      this.swalService.error("Delete failed. This client has already made a purchase.");
      return;
    }

    this
      .clientService
      .deleteClient(clientID)
      .pipe(
        switchMap(() => {
          return this.clientService.getAllClient();
        })
      )
      .subscribe({
      next: value => {
        // Handle success
        this.client = value;
        this.swalService.success("Client Deleted successfully");

      },
      error: err => {
        // Handle error
        console.error(err);
      }
    });


  }
  getArchive(){
    this.saleService.getAllSales().subscribe({
      next:value => {this.archive=value},
      error:err => {console.log(err)}
    })
  }

  returnClient(client:Client):Client{
    return this.cl=client;
  }

  onFileSelected(event: Event, client: Client) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];

    }
  }
  updateSalesCountForClients(): void {
    let archive: any[] = [];
    let clients: any[] = [];

    this.saleService.getAllSales().subscribe({
      next: (sales) => {
        archive = sales;
        this.clientService.getAllClient().subscribe({
          next: (clientsData) => {
            clients = clientsData;
            const salesByClient = archive.reduce((acc, sale) => {
              const clientId = sale.idClient;
              acc[clientId] = (acc[clientId] || 0) + 1;
              return acc;
            }, {});

            clients.forEach((client) => {
              const clientId = client.id;
              client.nbr_sales = salesByClient[clientId] || 0;
            });
          },
          error: (err) => {
            console.log(err);
          }
        });
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}


