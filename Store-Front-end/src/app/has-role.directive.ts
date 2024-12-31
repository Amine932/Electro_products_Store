import {Directive, Input, TemplateRef, ViewContainerRef} from "@angular/core";
import {KeycloakService} from "keycloak-angular";

@Directive({selector: '[hasRole]'})
export class HasRoleDirective {
  constructor(private templateRef: TemplateRef<any>, private viewContainer: ViewContainerRef, private keycloakService: KeycloakService) {
  }

  @Input() set hasRole(role: string | string[]) {
    role = Array.isArray(role) ? role : [role];

    const userHasRole = this.userHasRole(...role);

    if (userHasRole)  {
      this.viewContainer.createEmbeddedView(this.templateRef);
      return;
    }

    this.viewContainer.clear();
  }

  userHasRole(...role: string[]): boolean {
    if (!role)
      return true;

    role = Array.isArray(role) ? role : [role];

    const claims = this.keycloakService.getUserRoles() || [];

    return role.every(el => claims.includes(el));
  }
}
