import { Component, HostListener } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Product } from "src/app/modals/product";
import { Seller } from "src/app/modals/seller";
import { PaymentDataService } from "src/app/services/payment-data/payment-data.service";
import { ProductService } from "src/app/services/product.service";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-product-page",
  templateUrl: "./product-page.component.html",
  styleUrls: ["./product-page.component.css"],
})
export class ProductPageComponent {

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private userService: UserService,
    private router: Router,
  
  ) {}

  // for image gallery
  imgUrl = "../../../assets/";
  currentImage = 0;
  images = ["", "-1", "-2", "-3"];
  productId: string;
  product: Product;
  products1: Product[] = [];
  products2: Product[] = [];
  seller: Seller = {};

  ngOnInit(): void {
    this.productService.products$.subscribe(products => {
      this.products1 = products?.slice(0,4);
      this.products2 = products?.slice(4,8);
    });

    this.route.queryParams.subscribe(params => {
      this.scrollToTop();
      this.currentImage = 0;
      this.productId = params['id'];
      console.log("Product page - product id: ", this.productId);
      this.getProduct();
      this.getSeller();
    });
  }
  
  //data for email 
  productName:string="";



  scrollToTop() {
    window.scrollTo(0, 0);
  }

  // fetch seller info
  getSeller(): void {
    this.productService.fetchSellerForProduct(this.productId).subscribe(
      data => {
        console.log("--------------------------------Seller details-----------------------------");
        console.log(data);
        this.seller = data;
        
      }
    )
  }

  // fetch product info
  getProduct(): void {
    this.productService.getProductById(this.productId).subscribe(
      product => {
        console.log(product);
        this.product = product;
      },
      error => {
        console.error('Error fetching product:', error);
      }
    );
  }

  chat() {    

    this.userService.getUserEmail().subscribe(email => {
      const participantId1 = email;
      const participantId2 = this.seller.email;

      this.userService.isChat(participantId1, participantId2).subscribe(res => {
        if(res) {
          console.log("chat already there");
          this.router.navigate(['/chat']);
        }
        else {
          console.log("no previous chat");
          this.userService.createNewChat(participantId1, participantId2).subscribe(data => {
            console.log("new chat added")
            this.router.navigate(['/chat']);
          });
        }
      })

      
    })

  }

  // image slider - go to previous image
  prevImage() {
    if (this.currentImage > 0) {
      this.currentImage--;
      console.log(this.currentImage);
    }
  }

  // go to next image
  nextImage() {
    if (this.currentImage < this.images.length - 1) {
      this.currentImage++;
      console.log(this.currentImage);
    }
  }
}
