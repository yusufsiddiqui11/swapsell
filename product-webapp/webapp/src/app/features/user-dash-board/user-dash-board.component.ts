import { Component, OnInit } from "@angular/core";
import { Product } from "src/app/modals/product";
import { UserObj } from "src/app/modals/userObj";
import { ProductService } from "src/app/services/product.service";
import { UserService } from "src/app/services/user.service";
import { distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: "app-user-dash-board",
  templateUrl: "./user-dash-board.component.html",
  styleUrls: ["./user-dash-board.component.css"],
})
export class UserDashBoardComponent implements OnInit {
  constructor(
    private userService: UserService,
    private productService: ProductService
  ) {}

  user: UserObj = {};
  products: Product[] = [];

  ngOnInit(): void {

    this.userService.user$
    .pipe(distinctUntilChanged())
    .subscribe(user => {
      console.log("userdashboard user: ", user);
      this.user = user;

      this.productService.fetchProductsForUser(this.user.email).subscribe(data => {
        console.log("userdashboard products: ", data);
        this.products = data;
        console.log("userdashboard products 2: ", this.products);
      })

    });
  }
}
