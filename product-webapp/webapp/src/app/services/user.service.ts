import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { map, tap } from "rxjs/operators";
import { Message } from "../modals/message";
import { UserObj } from "../modals/userObj";

@Injectable({
  providedIn: "root",
})
export class UserService {
  constructor(private http: HttpClient) {}

  // url to fetch a chat between 2 participants
  URL = "http://localhost:8081/swapsell/api";

  private userSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  public user$ = this.userSubject.asObservable();
  private messageAddedSubject: Subject<void> = new Subject<void>();

  // fetch user data
  fetchUser(email: string) {
    this.http.get("http://localhost:8082/user/" + email).subscribe((data) => {
      console.log("user data in service", data);
      this.userSubject.next(data);
    });
  }

  removeUser() {
    this.userSubject.next({});
  }

  // Extract email from already fetched user data
  getUserEmail(): Observable<string> {
    return this.user$.pipe(map((user) => user?.email || ""));
  }

  // fetch all users who have contacted logged in user
  getUsers(participantId: string): Observable<any> {
    const params = new HttpParams().set("participantId", participantId);

    return this.http.get(`${this.URL}/chats/users`, { params });
  }

  updateUser(user: UserObj, email: string): Observable<any> {
    user.email = email;

    console.log("user service", user);

    return this.http.post("http://localhost:8082/user/update", user).pipe(
      tap(() => {
        this.fetchUser(email);
      })
    );
  }

  // check for existing chat between 2 users
  isChat(participantId1: string, participantId2: string): Observable<boolean> {
    const params = new HttpParams()
      .set("participantId1", participantId1)
      .set("participantId2", participantId2);

    return this.http.get<boolean>(`${this.URL}/chats/present`, { params }).pipe(
      map((response) => {
        return response;
      })
    );
  }

  // create a new chat between 2 users
  createNewChat(participantId1: string, participantId2: string) {
    console.log("creating chat in service", participantId1, participantId2);

    const url = `${this.URL}/chats`;
    const body = {
      participant1: participantId1,
      participant2: participantId2,
      message: "",
    };

    return this.http.post(url, body);
  }

  getMessageAddedObservable(): Observable<void> {
    return this.messageAddedSubject.asObservable();
  }

  notifyMessageAdded(): void {
    this.messageAddedSubject.next();
  }

  // fetch chat between 2 users
  getChat(participantId1: string, participantId2: string): Observable<any> {
    const params = new HttpParams()
      .set("participantId1", participantId1)
      .set("participantId2", participantId2);

    return this.http.get(`${this.URL}/chats`, { params });
  }

  addMessage(message: Message) {
    return this.http.post(`${this.URL}/chats/messages`, message);
  }
}
