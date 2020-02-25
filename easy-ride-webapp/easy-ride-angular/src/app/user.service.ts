import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class UserService {
  constructor(private http: HttpClient) {}

  getUserProfile(): Observable<any> {
    return this.http.get("/user/userprofile");
  }
  logUserOut(): Observable<any> {
    return this.http.get("/user/userlogout");
  }
}
