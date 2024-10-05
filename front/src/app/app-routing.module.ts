import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import { ListComponent } from './list/list.component';
import { authenticationGuard } from './guards/authentication.guard';
import { authorizationGuard } from './guards/authorization.guard';

const routes: Routes = [
  { path: "home", component: HomeComponent },

  {path: "admin", component: AdminTemplateComponent, canActivate:[authenticationGuard, authorizationGuard], children: [
    {path: "list", component: ListComponent}
  ]},

  { path: "", redirectTo: "home", pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
