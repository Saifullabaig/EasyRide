import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA
} from "@angular/material/dialog";
import { MapModelComponent } from "../map-model/map-model.component";

import { LoginDialogBoxComponent } from "src/app/login-dialog-box/login-dialog-box.component";

import { Router } from "@angular/router";
interface TimeSlot {
  value: string;
  viewValue: string;
}

export interface ResponseModel {
  statusCode: number;
  message: string;
}

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  _isDisabled: boolean;
  constructor(private router: Router, public dialog: MatDialog) {}

  ngOnInit() {}
  time: TimeSlot[] = [
    { value: "1pm", viewValue: "1pm" },
    { value: "2pm", viewValue: "2pm" },
    { value: "3pm", viewValue: "3pm" },
    { value: "4pm", viewValue: "4pm" },
    { value: "5pm", viewValue: "5pm" },
    { value: "6pm", viewValue: "6pm" },
    { value: "7pm", viewValue: "7pm" },
    { value: "8pm", viewValue: "8pm" },
    { value: "9pm", viewValue: "9pm" },
    { value: "10pm", viewValue: "10pm" },
    { value: "11pm", viewValue: "11pm" },
    { value: "12am", viewValue: "12am" },
    { value: "1am", viewValue: "1am" },
    { value: "2am", viewValue: "2am" },
    { value: "3pm", viewValue: "3am" },
    { value: "4am", viewValue: "4am" },
    { value: "5am", viewValue: "5am" },
    { value: "6am", viewValue: "6am" },
    { value: "7am", viewValue: "7am" },
    { value: "8am", viewValue: "8am" },
    { value: "9am", viewValue: "9am" },
    { value: "10am", viewValue: "10am" },
    { value: "11am", viewValue: "11am" },
    { value: "12am", viewValue: "12am" }
  ];

  getUrl() {
    // return "url(../assets/images/main.jpg)";
  }

  form = new FormGroup({
    city: new FormControl(""),
    tripDate: new FormControl(""),
    timeSlot: new FormControl(""),
    city_from: new FormControl({ value: "Banglore", disabled: true }),
    distance: new FormControl("")
  });
  todaydate: Date = new Date();
  set isDisabled(value: boolean) {
    this._isDisabled = value;
    if (value) {
      this.form.controls["city"].disable();
    } else {
      this.form.controls["city"].enable();
    }
  }
  show() {
    console.log("formvalue", this.form.value);
    const tripData = this.form.value;
    sessionStorage.setItem("tripData", JSON.stringify(tripData));
    if (document.cookie.length > 0) {
      this.router.navigate(["search"]);
    } else {
      this.dialog.open(LoginDialogBoxComponent, {
        width: "400px",
        height: "250px"
      });
    }
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(MapModelComponent, {
      width: "750px"
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("The dialog was closed");
      console.log("result------>", result.data.kmDistance);

      this.form.value.distance = result.data.kmDistance;

      this.form.value.city = result.data.destination;

      this.form.controls["distance"].setValue(result.data.kmDistance);
      this.form.controls["city"].setValue(result.data.destination.slice(0, 6));
      console.log("form final value-->", this.form.value);
    });
  }
}
