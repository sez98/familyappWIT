import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AppserviceService } from 'src/app/appservice.service';
import { GlobalVariables } from '../../globalVariable';

@Component({
  selector: 'app-career-advise',
  templateUrl: './career-advise.component.html',
  styleUrls: ['./career-advise.component.css'],
})
export class CareerAdviseComponent implements OnInit {
  addCareerForm = this.fb.group({
    topic: [''],
  });

  userDetails: any;
  userResponseCareer: any = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private addService: AppserviceService
  ) {
    this.userDetails = GlobalVariables.userDetails;
    console.log(this.userDetails);
  }

  ngOnInit(): void {}

  onSubmit() {
    console.log(this.addCareerForm.value);
    this.addService
      .getCareer(this.userDetails.username, this.addCareerForm.value.topic)
      .subscribe(
        (res: any) => {
          console.log(res);
          this.userResponseCareer = res;
        },
        (error: any) => {
          this.router.navigate(['dashboard']);
        }
      );
  }

  dashboard() {
    this.router.navigate(['dashboard']);
  }
}
