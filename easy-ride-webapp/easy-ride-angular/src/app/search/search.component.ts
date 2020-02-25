import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import { BookingService } from "../booking.service";
import * as moment from "moment";
import { Router } from "@angular/router";
@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"]
})
export class SearchComponent implements OnInit {
  public TripData: any;
  drivers: any;
  booking_form: FormGroup;
  pay = {bookId: '', price: 0};
  constructor(
    private book: BookingService,
    private cd: ChangeDetectorRef,
    private route: Router
  ) {
    this.booking_form = new FormGroup({
      destination: new FormControl({ value: "", disabled: true }),
      pickDate: new FormControl({ value: "", disabled: true }),
      pickTime: new FormControl({ value: "", disabled: true }),
      source: new FormControl({ value: "Banglore", disabled: true }),
      distance: new FormControl({ value: "", disabled: true }),
      driverId: new FormControl({ value: 0, disabled: true }),
      price: new FormControl({ value: 0, disabled: true })
    });
  }
  priceRate = 0;
  filter = 0;
  temp: any;
  driver: any;
  ngOnInit() {
    this.TripData = sessionStorage.getItem("tripData");
    console.log("Trip data from dashboard::", this.TripData);
    console.log("city----->", JSON.parse(this.TripData));
    const tripInfo = JSON.parse(this.TripData);
    console.log("dest-->", tripInfo.city);
    this.booking_form.controls["destination"].setValue(
      tripInfo.city.toString()
    );
    this.booking_form.controls["distance"].setValue(
      tripInfo.distance.toFixed(2)
    );
    this.booking_form.controls["pickDate"].setValue(
      moment(tripInfo.tripDate).format("DD-MM-YYYY")
    );
    this.booking_form.controls["pickTime"].setValue(
      moment(tripInfo.timeSlot, ["HH:mm"]).format("hh:mm A")
    );

    this.book.getDrivers().subscribe(data => {
      this.drivers = data;
      this.temp = this.drivers;
      console.log(this.temp + "temp");
    });
    this.book.getRate().subscribe(data => {
      this.driver = data;
      console.log("driadadadadadadad" + this.driver);
      this.priceRate = this.driver.driverprice * parseFloat(tripInfo.distance);
      console.log("total price: " + this.priceRate);
    });
  }

  // filter function
  Filter(lan: string) {
    if (lan === "English") {
      this.filter = 1;
      this.temp.forEach(element => {
        if (element.languages.includes("English")) {
          this.drivers = [];
        }
      });
      this.temp.forEach(element => {
        if (element.languages.includes("English")) {
          this.drivers.push(element);
        }
      });
      console.log(this.drivers);
      this.cd.detectChanges();
    } else if (lan === "Hindi") {
      this.filter = 2;
      this.temp.forEach(element => {
        if (element.languages.includes("Hindi")) {
          this.drivers = [];
        }
      });
      this.temp.forEach(element => {
        if (element.languages.includes("Hindi")) {
          this.drivers.push(element);
        }
      });
      console.log(this.drivers);
      this.cd.detectChanges();
    } else if (lan === "Kannada") {
      this.filter = 3;
      this.temp.forEach(element => {
        if (element.languages.includes("Kannada")) {
          this.drivers = [];
        }
      });
      this.temp.forEach(element => {
        if (element.languages.includes("Kannada")) {
          this.drivers.push(element);
        }
      });
      console.log(this.drivers);
      this.cd.detectChanges();
    } else {
      this.filter = 0;
      this.drivers = [];
      this.drivers = this.temp;
      this.cd.detectChanges();
    }
  }
  bookTickets() {
    this.booking_form.controls["driverId"].patchValue(this.driver.driverId);
    this.booking_form.controls["price"].setValue(this.priceRate);
    console.log(Object.values(this.booking_form.value));
    this.book.bookTicket(this.booking_form.value).subscribe(data=>{
      this.pay.bookId = data;
      this.pay.price = this.priceRate;
      this.book.postBooks(this.pay);
      this.routing();
    });
  }
  routing() {
    console.log("router");
    this.route.navigate(["payment"]).then(
      nav => {
        console.log(nav + "routing"); // true if navigation is successful
      },
      err => {
        console.log(err + "error routing"); // when there's an error
      }
    );
  }
}
