import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProductsComponent} from "./products/products.component";
import {ClientComponent} from "./clients/clients.component";
import {SalesComponent} from "./sales/sales.component";
import {ArchiveComponent} from "./archive/archive.component";
import {AuthGuard} from "./guards/auth.guard";
import {NgModule} from "@angular/core";

export const routes: Routes = [
  {path:'',title:'Home page',component:HomeComponent},
  {path:'home',title:'Home page',component:HomeComponent},
  {path:'products',title:'Product page',component:ProductsComponent},
  {path:'clients',title:'Client page',component:ClientComponent},
  {path:'sales',title:'Sale page',component:SalesComponent},
  {path:'archives',title:'Archive page',component:ArchiveComponent,canActivate : [AuthGuard], data : { roles : ['ADMIN']}},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }

