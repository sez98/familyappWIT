import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalVariables } from '../../globalVariable';
import { AppserviceService } from 'src/app/appservice.service';

@Component({
  selector: 'app-family',
  templateUrl: './family.component.html',
  styleUrls: ['./family.component.css'],
})
export class FamilyComponent implements OnInit {
  userDetails: any;
  userResponsePost: any = [];
  userResponseReview: any = [];
  userResponseEvent: any = [];
  userResponseBlood: any = [];
  userResponseHobbies: object = {};
  userResponseSocial: any;
  // @ts-ignore
  userResponseDisease: object = false;

  constructor(private router: Router, private addService: AppserviceService) {
    this.userDetails = GlobalVariables.userDetails;
    console.log(this.userDetails);
  }

  ngOnInit(): void {
    console.log(this.userDetails.username);

    this.addService.getEvent(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseEvent = res;
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.addService.getPost(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponsePost = res;
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.addService.getReview(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseReview = res;
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.addService.getHobbies(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseHobbies = res;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  paternalfamilyTree() {
    const queryParams: any = {};
    queryParams.id = JSON.stringify(0);
    this.router.navigate(['familyTree'], { queryParams });
  }

  maternalfamilyTree() {
    const queryParams: any = {};
    queryParams.id = JSON.stringify(1);
    this.router.navigate(['familyTree'], { queryParams });
  }

  addPost() {
    this.router.navigate(['addPost']);
  }

  addReview() {
    this.router.navigate(['addReview']);
  }

  dashboard() {
    this.router.navigate(['dashboard']);
  }

  blood() {
    this.addService.getBlood(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseBlood = res;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  social() {
    this.addService.getSocialIndex(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseSocial = res;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  disease() {
    this.addService.getDisease(this.userDetails.username).subscribe(
      (res: any) => {
        console.log(res);
        this.userResponseDisease = res;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  career() {
    this.router.navigate(['career']);
  }


}
