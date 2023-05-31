import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../modals/user';
import { UserService } from './user.service';
import { UserRegister } from '../modals/userRegister';
import { PopupService } from './popup.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  URL = "http://localhost:8080";
  isLoggedIn: boolean = false;
  jwt: string = "";
  message: string = "";

  getStatus(): Observable<boolean> {
    return of(this.isLoggedIn);
  }

  login(user: User): Observable<boolean> {
    return this.http.post(this.URL+"/login", user).pipe(map(data => {

      console.log(data);

      let json = JSON.stringify(data);

      this.jwt = JSON.parse(json).token;
      this.message = JSON.parse(json).message;
      let email = JSON.parse(json).email;

      console.log(this.jwt, this.message);

      sessionStorage.setItem("jwt", this.jwt);
      sessionStorage.setItem("message", this.message);
      
      if(this.message === "Login Successful") {
        this.isLoggedIn = true;

        // fetching user data on successful login
        this.userService.fetchUser(email);
      }
      else {
        this.isLoggedIn = false;
      }

      return this.isLoggedIn;

    }))  
  }

  logout(): void {
    this.isLoggedIn = false;
    this.userService.removeUser();
  } 

  register(user: UserRegister): Observable<any> {
    return this.http.post(this.URL+"/register", user);
  }

}
