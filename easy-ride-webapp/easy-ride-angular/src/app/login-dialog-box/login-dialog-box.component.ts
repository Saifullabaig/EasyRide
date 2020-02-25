import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-login-dialog-box',
  templateUrl: './login-dialog-box.component.html',
  styleUrls: ['./login-dialog-box.component.css']
})
export class LoginDialogBoxComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<LoginDialogBoxComponent>) { }

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close();
  }

}
