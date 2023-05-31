import { Component } from "@angular/core";
import { Product } from "src/app/modals/product";
import { ProductService } from "src/app/services/product.service";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent {
  products: Product[] = [];
  products1: Product[] = [];
  products2: Product[] = [];

  constructor(private productService: ProductService, private userService: UserService) {}

  ngOnInit(): void {
    this.productService.fetchProducts();
    this.productService.products$.subscribe((products) => {
      this.products = products;
      this.products1 = products?.slice(0, 4);
      this.products2 = products?.slice(4, 8);
    });
  }

  categories1 = [
    { name: "Automotive", image: "Automotive.jpg" },
    { name: "Real Estate", image: "RealEstate.jpg" },
    { name: "Electronics", image: "Electronics.jpg" },
    { name: "Home & Furniture", image: "HomeFurniture.jpg" },
  ];

  categories2 = [
    { name: "Automotive", image: "Automotive.jpg" },
    { name: "Real Estate", image: "RealEstate.jpg" },
    { name: "Electronics", image: "Electronics.jpg" },
    { name: "Home & Furniture", image: "HomeFurniture.jpg" },
  ];

}
