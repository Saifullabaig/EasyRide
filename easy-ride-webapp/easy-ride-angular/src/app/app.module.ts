import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AvatarModule } from 'ngx-avatar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatTabsModule} from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {FlexLayoutModule} from '@angular/flex-layout';
import {
  ReactiveFormsModule,
  FormsModule
} from '@angular/forms';
import {MatInputModule, MatProgressSpinnerModule} from '@angular/material';

import { AuthInterceptorService } from './auth-interceptor.service';
import {MatIconModule} from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import { MapModelComponent } from './map-model/map-model.component';
import {MatTableModule} from '@angular/material/table';
import { BookingHistoryComponent } from './booking-history/booking-history.component';
import { LoginDialogBoxComponent } from './login-dialog-box/login-dialog-box.component';
import {MatDialogModule} from '@angular/material/dialog';
import { PaymentComponent } from './payment/payment.component';
import { DemoMaterialModule } from './material-module';
import { SearchComponent } from './search/search.component';
import { DriverCardComponent } from './driver-card/driver-card.component';
import { ReviewComponent } from './review/review.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    MapModelComponent,
    PaymentComponent,
    BookingHistoryComponent,
 
    LoginDialogBoxComponent,
    SearchComponent,
    DriverCardComponent,
    ReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatCardModule,
    FlexLayoutModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatButtonModule,
    DemoMaterialModule,
    HttpClientModule,
    MatDialogModule,
    MatTableModule,
    AvatarModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
    },
    DemoMaterialModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
