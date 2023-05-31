import { style } from '@angular/animations';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { error, log } from 'console';
import { resolve } from 'dns';
import { url } from 'inspector';
import { promise } from 'protractor';
import { Order } from 'src/app/modals/Order';
import { PaymentService } from 'src/app/services/payment-service/payment.service';
import { ProductPageComponent } from '../product-page/product-page.component';
import { PaymentDataService } from 'src/app/services/payment-data/payment-data.service';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit  {



  submitForm(){
    console.log(this.orderData);
    this.paymentCheckOut();
    
  }
  constructor(private paymentService:PaymentService,){
    
  }
  ngOnInit(): void {
    console.log(this.orderData);

  }
  indianPrice:number;
  orderData:Order={
    price: 0,
    currency: 'USD',
    method: 'SALE',
    intent: 'PAYPAL',
    description: 'sell me'
  } 

  paymentCheckOut(){
    this.paymentService.createOrder(this.orderData).subscribe(data=>{
      // const redirectUrl = data.redirectUrl;
      const redirectUrl = data;
      console.log(JSON.stringify(redirectUrl));
      const redirectUrlObjToString = JSON.stringify(redirectUrl); 
      const parseObje = JSON.parse(redirectUrlObjToString);
      const theUrl = parseObje.redirectUrl;
      console.log(theUrl);
      window.location.href = theUrl;
    },error=>{
      console.error(error);  
    });
 
  }

  convertToUSD() {
    // Conversion rate from Indian Rupees to USD
    const conversionRate = 0.014;

    // Perform the conversion
    this.orderData.price = this.indianPrice * conversionRate;
  }

  
 
}
