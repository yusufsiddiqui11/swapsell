import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentService } from 'src/app/services/payment-service/payment.service';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.css']
})
export class PaymentSuccessComponent implements OnInit {
  constructor(private activatedRoute:ActivatedRoute,private paymentService:PaymentService){}
  ngOnInit(): void {
    // this.activatedRoute.paramMap.subscribe((parameter)=>{
    //   var paymentId:string = parameter.get("paymentId");
    //   var payerId :string = parameter.get("payerId");
    //   console.log(payerId + "  "+ paymentId);
    // this.paymentService.fetchOrderData().subscribe(data=>{
    //   console.log(data);
    // })
    }
}



