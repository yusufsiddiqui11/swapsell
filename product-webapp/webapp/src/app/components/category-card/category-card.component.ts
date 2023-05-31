import { Component, Input } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-category-card",
  templateUrl: "./category-card.component.html",
  styleUrls: ["./category-card.component.css"],
})
export class CategoryCardComponent {
  constructor(private router: Router) {}

  @Input()
  category: any;

  showResult() {
    const type = "category";
    const keyword = this.category.name;

    this.router.navigate(["/result"], { queryParams: { type, keyword } });
  }
}
