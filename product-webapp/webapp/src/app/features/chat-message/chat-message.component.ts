import { Component, Input, OnInit, OnDestroy, OnChanges, ElementRef, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Message } from 'src/app/modals/message';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent implements OnInit, OnDestroy, OnChanges {

  constructor(private userService: UserService) {}

  @Input()
  participantId1: string;

  @Input()
  participantId2: string; // this is email id

  messages = [];
  newMessage = "";
  currentDate: string;
  offer: boolean = false;
  dropdown: boolean = false;
  dropdownValues = ["Delete"];
  amount: number;

  private unsubscribe$: Subject<void> = new Subject<void>();

  ngOnInit(): void {
    this.fetchChat();

    this.userService.getMessageAddedObservable().pipe(
      takeUntil(this.unsubscribe$)
    ).subscribe(() => {
      this.fetchChat();
    });
    console.log(this.participantId2);
    
  }

  ngOnChanges(): void {
    this.fetchChat();
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  fetchChat(): void {
    if (this.participantId1 && this.participantId2) {
      this.userService.getChat(this.participantId1, this.participantId2).subscribe(chat => {
        this.messages = chat?.data.messages;
        this.scrollChatToBottom();
      });
    }
  }

  sendMessage() {
    this.calculateCurrentDate();

    const newMessageObj: Message = {
      "senderId": this.participantId1,
      "receiverId": this.participantId2,
      "content": this.newMessage,
      "timestamp": this.currentDate
    }
    console.log(newMessageObj);

    this.userService.addMessage(newMessageObj).subscribe((response: any) => {
      if (response.success) {
        console.log("Added message successfully");
        this.newMessage = "";
        this.userService.notifyMessageAdded();
      }
      else {
        console.log("Error adding message")
      }
    });
  }

  calculateCurrentDate() {
    const dateObj = new Date();
    const day = dateObj.getDate();
    const month = this.getMonthName(dateObj.getMonth());
    const year = dateObj.getFullYear();

    this.currentDate = `${day} ${month} ${year}`;
  }

  getMonthName(month: number): string {
    const monthNames = [
      'January', 'February', 'March', 'April', 'May', 'June', 
      'July', 'August', 'September', 'October', 'November', 'December'
    ];
    return monthNames[month];
  }

  // open/close dropdown
  toggleDropdown() {
    this.dropdown = !this.dropdown;
  }

  toggleDialogAndSendMsg(amount: number) {
    this.toggleDialog();
  
    if (!this.offer && amount) {
      this.newMessage = "I would like to buy this product for: ₹" + amount;
      this.sendMessage();
    }
  }
  
  toggleDialog() {
    this.offer = !this.offer; 
  }

  closeMessage() {
    this.participantId2 = "";
  }

  @ViewChild('chatContainer') chatContainer: ElementRef;

  private scrollChatToBottom() {
    setTimeout(() => {
      const container = this.chatContainer.nativeElement;
      container.scrollTop = container.scrollHeight;
    }, 0);
  }

  openPageInNewTab(): void {
    window.open('/payment', '_blank');
    console.log("This is participant 1");
    console.log(this.participantId1);
    console.log("This is participant 2");

  }
  
  

}