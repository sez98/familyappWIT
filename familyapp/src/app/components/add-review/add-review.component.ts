import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AppserviceService } from 'src/app/appservice.service';
import { GlobalVariables } from '../../globalVariable';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css'],
})
export class AddReviewComponent implements OnInit {
  addReviewForm = this.fb.group({
    username: [''],
    name: [''],
    topic: [''],
    description: [''],
  });
  userDetails: any;

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
    this.addReviewForm.value.username = this.userDetails.username;
    console.log(this.addReviewForm.value);
    this.addService.addReview(this.addReviewForm.value).subscribe(
      (res: any) => {
        console.log(res);
        this.router.navigate(['dashboard']);
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
