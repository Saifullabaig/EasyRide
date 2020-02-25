import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userProfileAvatarUrl: any;
  constructor(private router:Router,private userService: UserService, private d:ChangeDetectorRef) { }
  username: string;
  show = false;
  userProfile:any;
  userAvatar:any;
  ngOnInit() {
    this.userService.getUserProfile().subscribe(response => {
      this.userProfile = response;
      console.log('profile'+this.userProfile);
       if(this.userProfile != null){
   this.show = true;
    }
      this.userAvatar = this.userProfile.avatarURL;
      this.userProfileAvatarUrl = 'url(' + this.userAvatar + ')';
      this.username = this.userProfile.name;
      this.d.markForCheck();
    this.d.detectChanges();
    });
    this.d.markForCheck();
    this.d.detectChanges();
  }
//wfgetrhyjkjhghhgfds
getUserProfile() {
  this.userService.getUserProfile().subscribe(response => {
    this.userProfile = response;
    console.log('profile'+this.userProfile);
     if(this.userProfile != null){
 this.show = true;
  }
    this.userAvatar = this.userProfile.avatarURL;
    this.userProfileAvatarUrl = 'url(' + this.userAvatar + ')';
    this.username = this.userProfile.name;
  });
}
logout() {
  this.userService.logUserOut().subscribe(res => {
    this.router.navigate(['']);
    this.show = false;
  });
}

}
