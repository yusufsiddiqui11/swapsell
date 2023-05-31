import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-offer-dialog',
  templateUrl: './offer-dialog.component.html',
  styleUrls: ['./offer-dialog.component.css']
})
export class OfferDialogComponent {

  @Output()
  dialog = new EventEmitter<number>();

  amount: number;

  toggleDialog() {
    this.dialog.emit(this.amount);
  }

}