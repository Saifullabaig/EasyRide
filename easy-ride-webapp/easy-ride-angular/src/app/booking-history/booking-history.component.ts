import { Component, OnInit, ViewChild, ChangeDetectorRef } from "@angular/core";
import { DataSource } from "@angular/cdk/table";
import { MatPaginator } from "@angular/material";
import { BookingService } from '../booking.service';

export interface PeriodicElement {
  id: string;
  start: string;
  end: string;
  distance: number;
  fare: number;
  status: string;
  driver: string;
}

const ELEMENT_DATA: PeriodicElement[] = [];

@Component({
  selector: "app-booking-history",
  templateUrl: "./booking-history.component.html",
  styleUrls: ["./booking-history.component.css"]
})
export class BookingHistoryComponent implements OnInit {
  constructor(private book: BookingService, private cd:ChangeDetectorRef) {}

  dataSource = [];
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  ngOnInit() {
    this.book.getTickets().subscribe(data=>{
      this.dataSource = data;
      this.cd.detectChanges();
      this.cd.markForCheck();
    });
  }
  displayedColumns: string[] = [
    "start",
    "end",
    "distance",
    "fare",
    "driver",
    "trip_date",
    "time",
    "status"
  ];
}
