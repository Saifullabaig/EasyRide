import { Component, OnInit, Input, ChangeDetectorRef } from '@angular/core';
import { BookingService } from '../booking.service';
import { FormGroup ,FormControl} from '@angular/forms';

@Component({
  selector: 'app-driver-card',
  templateUrl: './driver-card.component.html',
  styleUrls: ['./driver-card.component.css']
})
export class DriverCardComponent implements OnInit {
  @Input() item: any;
  rate: number;
  cr: any;
  array:any;
  constructor(private book:BookingService, private cd: ChangeDetectorRef) { }
  price: number;
  status: boolean;
  ngOnInit() {
    this.rate = this.item.rating;
    this.cr = this.rate*20 +'%';
    this.price = this.item.driverprice;
    this.book.getStatus().subscribe(data=>{
      this.status = data;
      this.cd.markForCheck();
    }); 
  }

  booking(){
    this.status = false; 
    this.book.pushStatus(false);
    this.book.postRate(this.item);
  }

  cancel(){
    this.status = true;
    this.book.pushStatus(true);
    this.book.postRate(Object);
  }
}
