import { Injectable } from '@angular/core';
import { Seller } from 'src/app/modals/seller';

@Injectable({
  providedIn: 'root'
})
export class PaymentDataService {

  constructor() { }

  private sellerdata:Seller;
  setData(data:Seller){
    this.sellerdata=data
  }
  getData() {
    return this.sellerdata;
  }
}
