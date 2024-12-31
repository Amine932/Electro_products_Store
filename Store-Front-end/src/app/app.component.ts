import {APP_INITIALIZER, Component, NgModule, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Location} from "@angular/common";
import {KeycloakEventType, KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'Store-Front-end';

  public profile? : KeycloakProfile;

  constructor(public router:Router,public keycloakService:KeycloakService) {}

  ngOnInit() {
    if(this.keycloakService.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile=>{
        this.profile=profile;
      });
    }

    this
      .keycloakService
      .keycloakEvents$
      .subscribe(({type, args}) => {
        if (KeycloakEventType.OnAuthLogout  === type) {
          this.keycloakService.login();
          return;
        }

        if  (KeycloakEventType.OnTokenExpired === type) {
          this.keycloakService.updateToken(200);
          return;
        }

        if (KeycloakEventType.OnAuthRefreshError === type) {
          this.logout();
          return;
        }
      })
  }

  logout() {
    this.keycloakService.logout(window.location.origin)
  }



}
