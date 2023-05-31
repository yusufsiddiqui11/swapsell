import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor(private snackBar: MatSnackBar) { }

  open(msg: string, duration: number) {
    this.snackBar.open(msg, "", {duration: duration})
  }
  
}
