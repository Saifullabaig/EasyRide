import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MapModelComponent } from './map-model/map-model.component';
import { BookingHistoryComponent } from '../app/booking-history/booking-history.component';


import { DashboardComponent } from 'src/app/dashboard/dashboard.component';
import { LoginDialogBoxComponent } from 'src/app/login-dialog-box/login-dialog-box.component';
import { PaymentComponent } from './payment/payment.component';
import { SearchComponent } from './search/search.component';


const routes: Routes = [
  {path: 'map' , component: MapModelComponent},
  {path: '', component: DashboardComponent},
  {path: 'history', component:BookingHistoryComponent},
  {path: 'search', component: SearchComponent},
  { path: 'login', component: LoginDialogBoxComponent },
  { path: 'payment', component: PaymentComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
