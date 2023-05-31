import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserObj } from 'src/app/modals/userObj';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private route: Router
  ) {}

  keyword: string = "";
  isMenuOpen: boolean = false;
  isLoggedIn: boolean = false;
  user: UserObj = {}
  userInitials = "";

  ngOnInit(): void {
    this.userService.user$.subscribe(data => {
      this.user = data;
      this.generateInitials();
    })

    this.authService.getStatus().subscribe(data => {
      this.isLoggedIn = data;
    })
  }

  search() {
    const type = "search";
    const keyword = this.keyword;

    this.route.navigate(['/result'], { queryParams: { type, keyword }});
  }

  logout() {
    this.authService.logout();
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  generateInitials() {
    if(this.user) {
      this.userInitials = this.userInitials + this.user.firstName.substring(0,1) + this.user.lastName.substring(0,1);
    }
  }

}
