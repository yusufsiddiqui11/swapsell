import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/modals/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {

  constructor(private router: Router) {}

  @Input()
  product: Product;

  goToProductPage(productId: string) {
    this.router.navigate(['/product'], { queryParams: { id: productId } });
  }

}
