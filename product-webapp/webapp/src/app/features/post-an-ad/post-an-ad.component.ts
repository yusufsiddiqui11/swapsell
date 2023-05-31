import { Component } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { PopupService } from "src/app/services/popup.service";
import { ProductService } from "src/app/services/product.service";

@Component({
  selector: "app-post-an-ad",
  templateUrl: "./post-an-ad.component.html",
  styleUrls: ["./post-an-ad.component.css"],
})
export class PostAnAdComponent {
  constructor(
    private fb: FormBuilder, 
    private popup: PopupService,
    private productService: ProductService,
    private router: Router
  ) {}

  addressForm = this.fb.group({
    name: [null, Validators.required],
    title: [null, Validators.required],
    description: [null, Validators.required],
    price: [null, Validators.required],
    category: [null, Validators.required],
    condition: [null, Validators.required],
    ageInDays: [null, Validators.required],
    address: [null, Validators.required],
    city: [null, Validators.required],
    state: [null, Validators.required],
    pinCode: [
      null,
      Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(8),
      ]),
    ]
  });

  hasUnitNumber = false;

  states = [
    { name: "Andhra Pradesh", abbreviation: "AP" },
    { name: "Arunachal Pradesh", abbreviation: "" },
    { name: "Assam", abbreviation: "AS" },
    { name: "Bihar", abbreviation: "AZ" },
    { name: "Chattisgarh", abbreviation: "AR" },
    { name: "Goa", abbreviation: "CA" },
    { name: "Gujarat", abbreviation: "CO" },
    { name: "Haryana", abbreviation: "CT" },
    { name: "Himachal Pradesh", abbreviation: "DE" },
    { name: "Karnataka", abbreviation: "DC" },
    { name: "Kerala", abbreviation: "FM" },
    { name: "Madhya Pradesh", abbreviation: "FL" },
    { name: "Maharastra", abbreviation: "GA" },
    { name: "Manipur", abbreviation: "GU" },
    { name: "Meghalaya", abbreviation: "HI" },
    { name: "Odisha", abbreviation: "ID" },
    { name: "Nagaland", abbreviation: "IL" },
    { name: "Punjab", abbreviation: "IN" },
    { name: "Rajasthan", abbreviation: "IA" },
    { name: "Sikkim", abbreviation: "KS" },
    { name: "Tamil Nadu", abbreviation: "KY" },
    { name: "Telangana", abbreviation: "LA" },
    { name: "Uttarakand", abbreviation: "ME" },
    { name: "Uttar Pradesh", abbreviation: "MH" },
    { name: "West Bengal", abbreviation: "MD" },
  ];
  category = [
    { name: "Automotive" },
    { name: "Real Estate" },
    { name: "Electronics" },
    { name: "Home & Furniture" }
  ];

  // images: { url: string }[] = [];
  images: string[] = [];

  onFileSelected(event: any) {
    const files = event.target.files;
    if (files && files.length > 0 && this.images.length < 10) {
      // if (!this.images[0]) {
      //   this.images[0] = [];
      // }
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        console.log(file.name);
        this.images.push(file.name);
      }
    } else if (this.images.length >= 10) {
      this.popup.open("Image maximum limit reached!", 2000);
    }
  }  

  removeImage(index: number) {
    this.images.splice(index, 1);

    console.log(this.images);
  }

  onSubmit(): void {
    console.log(this.images);
    this.productService.addProduct(this.addressForm.value, this.images).subscribe(result => {
      if (result) {
        this.popup.open("Product added successfully", 2000);
        this.router.navigate(['']);
      } else {
        this.popup.open("Failed to add product", 2000);
      }
    });;
    this.popup.open("Ad posted successfully", 2000);
  }

}