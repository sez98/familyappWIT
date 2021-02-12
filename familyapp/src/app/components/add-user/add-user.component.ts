import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AppserviceService } from 'src/app/appservice.service';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css'],
})
export class AddUserComponent implements OnInit {
  addUserForm = this.fb.group({
    username: [''],
    email: [''],
    name: [''],
    password: [''],
    dob: null,
    doa: null,
    gender: null,
    mom_username: null,
    dad_username: null,
    spouse_username: null,
    married: [''],
    state: null,
    country: null,
    bloodGroup: null,
    profession: null,
    children: this.fb.array([this.fb.control('')]),
    disease: this.fb.array([this.fb.control('')])
  });

  get children() {
    return this.addUserForm.get('children') as FormArray;
  }
  get disease() {
    return this.addUserForm.get('disease') as FormArray;
  }
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private addService: AppserviceService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    console.log(this.addUserForm.value);
    this.addService.addUser(this.addUserForm.value).subscribe(
      (res: any) => {
        console.log(res);
        this.router.navigate(['']);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  addchildren() {
    this.children.push(this.fb.control(''));
    console.log('123');
  }

  adddisease() {
    this.disease.push(this.fb.control(''));
    console.log('123');
  }
}
