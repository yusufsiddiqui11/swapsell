import { animate, animation, state, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';


@Component({
  selector: 'app-users-details',
  templateUrl: './users-details.component.html',
  styleUrls: ['./users-details.component.css'],
  animations:[
    trigger('fade',[
      state('void',style({opacity:0})),
      transition(':enter,:leave',[animate(2000)])
    ])
  ]
})
export class UsersDetailsComponent {
 isMale:boolean=true;
 isFemale:boolean=false;
 imageUrl: string="https://c4.wallpaperflare.com/wallpaper/586/603/742/minimalism-4k-for-mac-desktop-wallpaper-preview.jpg";

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageUrl = reader.result as string;
      };
    }
  }
}








