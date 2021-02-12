import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { AppserviceService } from 'src/app/appservice.service';
import { GlobalVariables } from '../../globalVariable';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  userDetails: any = [];

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private addService: AppserviceService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    console.log(this.loginForm.value);
    this.addService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (res: any) => {
          if (res != null) {
            GlobalVariables.userDetails = res;
            this.userDetails = GlobalVariables.userDetails;
            this.router.navigate(['dashboard']);
          } else {
            window.location.reload();
          }
        },
        (error: any) => {
          console.log(error);
        }
      );
  }

  addUser() {
    this.router.navigate(['addUser']);
  }
}
