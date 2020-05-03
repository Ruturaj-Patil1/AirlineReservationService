import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { StartComponent } from './components/start/start.component';
import { ViewFilghtsComponent } from './components/view-filghts/view-filghts.component';
import { BookComponent } from './components/book/book.component';
//import { CancelComponent } from './components/cancel/cancel.component';
import { TicketsComponent } from './components/tickets/tickets.component';
import { NavbarComponent }  from './components/navbar/navbar.component';

const routes: Routes = [
  {path:'', component:StartComponent},
  {path:'home', component:HomeComponent},
  {path:'login', component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'start',component:StartComponent},
  {path:'flights',component:ViewFilghtsComponent},
  {path:'book',component:BookComponent},
  //{path:'cancel',component:CancelComponent},
  {path:'tickets',component:TicketsComponent},
  {path:'navbar',component:NavbarComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
