import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app.routes';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import {HttpClientModule} from "@angular/common/http";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {CommonModule} from "@angular/common";
import {RouterLink, RouterOutlet} from "@angular/router";
import {NgToastModule} from "ng-angular-popup";
import {AuthGuard} from "./guards/auth.guard";
import {ArchiveComponent} from "./archive/archive.component";
import {ClientComponent} from "./clients/clients.component";
import {HomeComponent} from "./home/home.component";
import {SalesComponent} from "./sales/sales.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HasRoleDirective} from "./has-role.directive";
import {SwalService} from "./services/swal.service";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://keycloak:8080',
        realm: 'my_store',
        clientId: 'front_client'
      },
      bearerExcludedUrls: ['/assets'],
      loadUserProfileAtStartUp: false,
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe : true,
        enableLogging: true,
        // silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    HasRoleDirective,
    AppComponent,
    ArchiveComponent,
    ClientComponent,
    HomeComponent,
    SalesComponent,
    ProductsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    RouterOutlet,
    RouterLink,
    NgToastModule,
    HttpClientModule,
    KeycloakAngularModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    SwalService,
    AuthGuard,
    {provide : APP_INITIALIZER, deps : [KeycloakService],useFactory : initializeKeycloak, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
