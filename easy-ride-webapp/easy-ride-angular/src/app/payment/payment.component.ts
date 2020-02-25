import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import {
  MomentDateAdapter,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS
} from "@angular/material-moment-adapter";
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE
} from "@angular/material/core";
import { MatDatepicker } from "@angular/material/datepicker";
import * as _moment from "moment";
import { default as _rollupMoment, Moment } from "moment";
import { BookingService } from "../booking.service";
import { Router } from "@angular/router";
const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: "MM/YYYY"
  },
  display: {
    dateInput: "MM/YYYY",
    monthYearLabel: "MMM YYYY",
    dateA11yLabel: "LL",
    monthYearA11yLabel: "MMMM YYYY"
  }
};

@Component({
  selector: "app-payment",
  //   template: `<div>
  //    <p>Today is {{today | date}}</p>
  //    <p>Or if you prefer, {{today | date:'fullDate'}}</p>
  //    <p>The time is {{today | date:'h:mm a z'}}</p>
  //  </div>`,
  templateUrl: "./payment.component.html",
  styleUrls: ["./payment.component.css"],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class PaymentComponent implements OnInit {
  public cardForm: FormGroup;
  books: any;
  showLoader: boolean;
  priceRate: any;
  constructor(private book: BookingService, private route: Router) {}
  isActive = false;
  paymentInfo = { cardNumber: "", cardName: "", bookingId: "" };
  ngOnInit() {
    this.cardForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      number: new FormControl("", [
        Validators.required,
        Validators.minLength(16)
      ]),
      cvv: new FormControl("", [Validators.required, Validators.minLength(3)])
    });
    this.book.getBooks().subscribe(data => {
      this.books = data;
      this.priceRate = this.books.price;
    });
  }
  currentDate: Date = new Date();
  public hasError = (controlName: string, errorName: string) => {
    return this.cardForm.controls[controlName].hasError(errorName);
  };
  date = new FormControl(moment(), [Validators.required]);

  chosenYearHandler(normalizedYear: Moment) {
    const ctrlValue = this.date.value;
    ctrlValue.year(normalizedYear.year());
    this.date.setValue(ctrlValue);
    console.log("year::", ctrlValue);
  }

  chosenMonthHandler(
    normalizedMonth: Moment,
    datepicker: MatDatepicker<Moment>
  ) {
    const ctrlValue = this.date.value;
    ctrlValue.month(normalizedMonth.month());
    this.date.setValue(ctrlValue);
    datepicker.close();
    console.log("month::", ctrlValue);
  }
  public onCancel = () => {
    this.route.navigate(["search"]);
  };

  onPay() {
    this.showLoader = true;
    setTimeout(() => {
      this.showLoader = false;
      this.book.payment(this.paymentInfo).subscribe(response => {
        this.route.navigate(["history"]);
      });
    }, 3000);
  }

  public payment = cardFormValue => {
    if (this.cardForm.valid) {
      console.log("inside payment::", cardFormValue);
      this.paymentInfo.cardNumber = cardFormValue.number;
      this.paymentInfo.cardName = cardFormValue.name;
      this.paymentInfo.bookingId = this.books.bookId;
      console.log("inside ::", this.paymentInfo);
      //your task after delay.
      // this.loaded=true;
      this.onPay();
    }
  };
}
