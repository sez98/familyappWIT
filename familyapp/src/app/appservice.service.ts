import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

var baseUrl = 'http://localhost:8082/familyapp';
@Injectable({
  providedIn: 'root',
})
export class AppserviceService {
  constructor(private http: HttpClient) {}

  login(username: string, password: any) {
    return this.http.get(
      baseUrl + '/login?' + 'username=' + username + '&password=' + password
    );
  }

  addUser(user: any) {
    return this.http.post(baseUrl + '/adduser', user);
  }

  addPost(inputData: any) {
    return this.http.post(baseUrl + '/addPost', inputData);
  }

  addReview(inputData: any) {
    return this.http.post(baseUrl + '/addReview', inputData);
  }

  familytree(username: any, i: any) {
    return this.http.get(
      baseUrl + '/getfamily?' + 'username=' + username + '&i=' + i
    );
  }

  getPost(username: any) {
    return this.http.get(baseUrl + '/getposts?' + 'username=' + username);
  }

  getEvent(username: any) {
    return this.http.get(baseUrl + '/getEvents?' + 'username=' + username);
  }

  getReview(username: any) {
    return this.http.get(baseUrl + '/getreviews?' + 'username=' + username);
  }

  getHobbies(username: any) {
    return this.http.get(baseUrl + '/getHobbies?' + 'username=' + username);
  }

  getBlood(username: any) {
    return this.http.get(
      baseUrl + '/matchingbloodgroup?' + 'username=' + username
    );
  }

  getCareer(username: any, i: any) {
    return this.http.get(
      baseUrl + '/getcareeradvise?' + 'username=' + username + '&i=' + i
    );
  }

  getSocialIndex(username: any){
    return this.http.get(
       baseUrl + '/socialindex?' + 'username=' + username
    );
  }

  getDisease(username: any){
    return this.http.get(
       baseUrl + '/diseases?' + 'username=' + username
    );
  }

}
