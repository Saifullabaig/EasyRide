import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    responseType: "text" as "json"
};
@Injectable({
  providedIn: 'root'
})
export class BookingService {
  
  private subject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);
  private driver: BehaviorSubject<Object> = new BehaviorSubject<Object> (Object);
  private bookDetail: BehaviorSubject<Object> = new BehaviorSubject<Object> (Object);
  constructor(private http: HttpClient) { }

  getBooks(){
    return this.bookDetail.asObservable();
  }

  postBooks(data){
    this.bookDetail.next(data);
  }

  getStatus(){
    return this.subject.asObservable();
  }

  pushStatus(data){
    this.subject.next(data);
  }

  getRate(){
    return this.driver.asObservable();
  }

  postRate(data){
    this.driver.next(data);
  }
  getDrivers(): Observable<any> {
    return this.http.get(`/book/driver`);
  }

  bookTicket(data:any): Observable<any> { 
    return this.http.post('/book/booking',data,httpOptions);
  }

  payment(data): Observable<any> {
    return this.http.post('/book/payment',data,httpOptions);
  }

  getTickets(): Observable<any>{
    return this.http.get('/book/booking');
  }
}
