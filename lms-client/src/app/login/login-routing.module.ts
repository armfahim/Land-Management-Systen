import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ForgotComponent } from './components/forgot/forgot.component';
import { RegisterComponent } from './components/register/register.component';
import { OtpComponent } from './components/otp/otp.component';
import { LockscreenComponent } from './components/lockscreen/lockscreen.component';


const routes: Routes = [
    {path: '' , component: LoginComponent },
    {path:'forgot', component:ForgotComponent},
    {path:'register',component:RegisterComponent},
    {path:'otp',component:OtpComponent},
    {path:'lockscreen',component:LockscreenComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
