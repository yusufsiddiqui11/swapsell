import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  
  constructor(private userService: UserService) {}

  participantId1: string; // id of logged user 
  participantId2: string; // id of other user 

  ngOnInit(): void {
    this.userService.user$.subscribe(data => {
      console.log("userId", data.id);
      this.participantId1 = data.email;
    }) 
  }

  receiveId(id: string) {
    this.participantId2 = id;
  }

}
