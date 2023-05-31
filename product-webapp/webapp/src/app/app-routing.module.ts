import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './features/login/components/login/login.component';
import { RegisterComponent } from './features/register/components/register/register.component';
import { UserDashBoardComponent } from './features/user-dash-board/user-dash-board.component';
import { UpdateUserDataComponent } from './features/update-user-data/update-user-data.component';
import { PostAnAdComponent } from './features/post-an-ad/post-an-ad.component';
import { ProductResultComponent } from './features/product-result/product-result.component';
import { ProductPageComponent } from './features/product-page/product-page.component';
import { UsersDetailsComponent } from './features/users-details/users-details.component';
import { HomeComponent } from './features/home/home.component';
import { PaymentComponent } from './features/payment/payment.component';
import { PaymentSuccessComponent } from './features/payment-success/payment-success.component';
import { ChatComponent } from './features/chat/chat.component';
import { AuthGuard } from './guard/auth.guard';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "result", component: ProductResultComponent},
  {path: "product", component: ProductPageComponent},
  {
    path:"userDashBoard",
    component:UserDashBoardComponent,
    canActivate: [AuthGuard]
  },
  {
    path:"editDetails",
    component:UpdateUserDataComponent,
    canActivate: [AuthGuard]
  },{
    path:"postAnAdd",
    component:PostAnAdComponent,
    canActivate: [AuthGuard]
  },{
    path:"userDetails",
    component:UsersDetailsComponent,
    canActivate: [AuthGuard]
  },{
    path:"payment",
    component:PaymentComponent,
    // canActivate: [AuthGuard]
  },{
    path:"paypal/success-component",
    component:PaymentSuccessComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: "chat",
    component: ChatComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "**",
    component: NotFoundComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
