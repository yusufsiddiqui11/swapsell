import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/modals/user';
import { AuthService } from 'src/app/services/auth.service';
import { PopupService } from 'src/app/services/popup.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private popup: PopupService
  ) { }

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  ngOnInit(): void {
    this.scrollToTop();
  }

  user: User = {
    email: "",
    password: "",
  }

  login() {
    this.authService.login(this.user).subscribe(isLoggedIn => {
      console.log('login comp', isLoggedIn);
  
      if (isLoggedIn) {
        this.popup.open("Welcome", 2000);
        this.router.navigate(['/']);
      } 
      else {
        this.popup.open("Invalid Credentials!", 2000);
      }
    });
  }

}
