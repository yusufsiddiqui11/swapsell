import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat-menu',
  templateUrl: './chat-menu.component.html',
  styleUrls: ['./chat-menu.component.css']
})
export class ChatMenuComponent implements OnInit {

  constructor(private userService: UserService) {}

  @Input()
  participantId1: string;

  users = [];

  ngOnInit(): void {

    this.userService.getUsers(this.participantId1).subscribe(res => {
      console.log(res.data);
      this.users = res?.data;
    })

  }

  @Output()
  participantId2 = new EventEmitter<string>();

  goToMessage(id: string) {
    this.participantId2.emit(id);
  }

}