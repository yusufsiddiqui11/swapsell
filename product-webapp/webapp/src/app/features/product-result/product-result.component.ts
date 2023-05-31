import { Component, OnInit, HostListener } from "@angular/core";
import { ActivatedRoute, Route } from "@angular/router";
import { Subscription } from "rxjs";
import { Product } from "src/app/modals/product";
import { ProductService } from "src/app/services/product.service";

@Component({
  selector: "app-product-result",
  templateUrl: "./product-result.component.html",
  styleUrls: ["./product-result.component.css"],
})
export class ProductResultComponent implements OnInit {
  products: Product[] = [];
  dropdown: boolean = false;
  dropdownValues = [
    "recommended",
    "price - low to high",
    "price - high to low",
  ];
  sort: string = "recommended";

  filter: boolean = false;
  filterData = {
    toggle: false,
    filters: {
      fromPrice: null,
      toPrice: null,
      isNew: null,
      isGood: null,
      isUsed: null,
      age: null,
    },
  };
  filteredProducts: Product[];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  scrollToTop() {
    window.scrollTo(0, 0);
  }

  routeSubscription: Subscription;
  ngOnInit(): void {
    this.filter = window.innerWidth > 576;

    this.routeSubscription = this.route.queryParams.subscribe((params) => {
      this.scrollToTop();
      const keyword = params["keyword"];
      const type = params["type"];
      console.log(keyword);

      if (type == "search") {
        this.productService
          .getProductsByKeyword(keyword)
          .subscribe((products) => {
            console.log(products);
            this.products = products;
            this.filteredProducts = this.products;
          });
      }

      if (type == "category") {
        this.productService
          .getProductsByCategory(keyword)
          .subscribe((products) => {
            console.log(products);
            this.products = products;
            this.filteredProducts = this.products;
          });
      }
    });
  }

  ngOnDestroy() {
    this.routeSubscription.unsubscribe();
  }

  @HostListener("window:resize", ["$event"])
  onResize(event: any) {
    if (event.target.innerWidth > 576) {
      this.filter = true;
    }
  }

  toggleFilter(newFilter: boolean) {
    this.filter = newFilter;
  }

  // change sort value
  updateSort(newSort: string) {
    this.sort = newSort;
    this.toggleDropdown();
    this.handleSort();
  }

  // open/close sort-dropdown
  toggleDropdown() {
    this.dropdown = !this.dropdown;
  }

  // filter result
  handleFilterEvent(filterData: any) {
    console.log(filterData);
    this.scrollToTop();

    const { filters } = filterData;
    const { fromPrice, toPrice, isNew, isGood, isUsed, age } = filters;

    this.filteredProducts = this.products.filter((product) => {
      if (fromPrice && product.price < fromPrice) {
        return false;
      }
      if (toPrice && product.price > toPrice) {
        return false;
      }

      let conditionMatched = false;

      if (isNew && product.condition.toLowerCase() === "new") {
        conditionMatched = true;
      }

      if (isGood && product.condition.toLowerCase() === "good") {
        conditionMatched = true;
      }

      if (isUsed && product.condition.toLowerCase() === "used") {
        conditionMatched = true;
      }

      if (!conditionMatched) {
        return false;
      }

      if (age) {
        const ageInDays = product.ageInDays;
        if (age == '90' && ageInDays >= 90) {
          return false;
        }
        if (age == '135' && (ageInDays < 90 || ageInDays > 180)) {
          return false;
        }
        if (age == '180' && ageInDays <= 180) {
          return false;
        }
      }
      

      return true;
    });

  }

  handleSort() {
    switch (this.sort) {
      case "recommended":
        break;
      case "price - low to high":
        this.filteredProducts.sort((a, b) => a.price - b.price);
        break;
      case "price - high to low":
        this.filteredProducts.sort((a, b) => b.price - a.price);
        break;
      default:
        break;
    }
  }

}
