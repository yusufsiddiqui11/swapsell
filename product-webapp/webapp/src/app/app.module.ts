import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './features/login/components/login/login.component';
import { RegisterComponent } from './features/register/components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { MatInputModule } from '@angular/material/input';
import { UserDashBoardComponent } from './features/user-dash-board/user-dash-board.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTabsModule} from '@angular/material/tabs';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatRadioModule} from '@angular/material/radio';
import { UpdateUserDataComponent } from './features/update-user-data/update-user-data.component';
import { MatSelectModule } from '@angular/material/select';
import {MatTooltipModule} from '@angular/material/tooltip';
import { HeaderComponent } from './features/header/header.component';
import { PostAnAdComponent } from './features/post-an-ad/post-an-ad.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { ProductResultComponent } from './features/product-result/product-result.component';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { FilterComponent } from './components/filter/filter.component';
import {MatSliderModule} from '@angular/material/slider';
import { ProductPageComponent } from './features/product-page/product-page.component';
import { UsersDetailsComponent } from './features/users-details/users-details.component';
import { HomeComponent } from './features/home/home.component';
import { CategoryCardComponent } from './components/category-card/category-card.component';
import { ReviewCardComponent } from './components/review-card/review-card.component';
import { ProductService } from './services/product.service';
import { HttpClientModule } from '@angular/common/http';
import { ChatMenuComponent } from './features/chat-menu/chat-menu.component';
import { ChatMessageComponent } from './features/chat-message/chat-message.component';
import { ChatComponent } from './features/chat/chat.component';
import { MessageComponent } from './components/message/message.component';
import { PaymentComponent } from './features/payment/payment.component';
import { PaymentSuccessComponent } from './features/payment-success/payment-success.component';
import { OfferDialogComponent } from './components/offer-dialog/offer-dialog.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { NotFoundComponent } from './components/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    UserDashBoardComponent,
    UpdateUserDataComponent,
    PostAnAdComponent,
    ProductResultComponent,
    ProductCardComponent,
    FilterComponent,
    ProductPageComponent,
    UsersDetailsComponent,
    HomeComponent,
    CategoryCardComponent,
    ReviewCardComponent,
    PaymentComponent,
    PaymentSuccessComponent,
    ChatMenuComponent,
    ChatMessageComponent,
    ChatComponent,
    MessageComponent,
    OfferDialogComponent,
    MessageComponent,
    NotFoundComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    MatTabsModule,
    MatRadioModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatTooltipModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatSnackBarModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }