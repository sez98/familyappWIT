import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AppserviceService } from 'src/app/appservice.service';
import { GlobalVariables } from '../../globalVariable';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css'],
})
export class AddPostComponent implements OnInit {
  addPostForm = this.fb.group({
    username: [''],
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
    console.log(this.addPostForm.value);
    this.addPostForm.value.username = this.userDetails.username;
    console.log(this.addPostForm.value);
    this.addService.addPost(this.addPostForm.value).subscribe(
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
