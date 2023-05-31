import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/app/modals/Order';
import { PaymentSuccess } from 'src/app/modals/paymentSuccess';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  baseUrl:string="http://localhost:8084/pay";
  constructor(private httpClient:HttpClient) { }

  createOrder(orderData:Order):Observable<Order>{
    console.log("in the service");
    
    console.log(orderData);
    
    return this.httpClient.post<Order>(`${this.baseUrl}/payment`,orderData);
  }

  fetchOrderData():Observable<any>{
    return this.httpClient.get("http://localhost:8084/pay/target-page");
  }
 
}
