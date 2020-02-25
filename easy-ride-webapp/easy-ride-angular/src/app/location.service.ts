import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }

  getLocationName(long: any, lat: any): Observable<any> {
    return this.http.get(`http://nominatim.openstreetmap.org/reverse?format=json&lon=${long}&lat=${lat}`);
  }

  getLocationByName(local: string): Observable<any>{
    const localD = Object.values(local);
    return this.http.get(`https://nominatim.openstreetmap.org/search?q=${localD}&format=json&polygon=1&addressdetails=1`);
  }
}
