import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";
import { PopupService } from "src/app/services/popup.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  constructor(
    private authService: AuthService, 
    private popup: PopupService,
    private router: Router
    ) {}

  user = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
  };

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  ngOnInit(): void {
    this.scrollToTop();
  }

  isPasswordDiff: boolean = true;
  checkPassword() {
    if (this.user.password === this.user.confirmPassword)
      this.isPasswordDiff = false;
    else this.isPasswordDiff = true;
    console.log(
      this.isPasswordDiff,
      this.user.password,
      this.user.confirmPassword
    );
  }

  register() {
    const userToSend = {
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email,
      password: this.user.password,
    };

    this.authService.register(userToSend).subscribe((data) => {
      this.popup.open("Account created successfully!", 2000);
      this.router.navigate(['/login']);
    });
  }
  
}
