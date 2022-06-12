import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from "@angular/forms";
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Login } from '../../models/login';
import { LoginService } from '../../services/login.service';
import { delay } from "rxjs/operators";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  id!: string;
  isAddMode!: boolean
  loading = false;
  submitted = false;
  isLoggedIn : any;
  

  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private loginService: LoginService,
    private router: Router,
    // private hrcremp:EmployeeService,
    ) { }

  ngOnInit() {
    this.initializeForm();
    this.initButtonRippleEffect();
    if(this.loginService.isLoggedIn()){
      console.log("Inside on init of login component");
      this.router.navigate(['dashboard']);
    }
  }

  get f() { return this.loginForm.controls; }

  initializeForm(){

      this.loginForm=this.formBuilder.group({
        username:["",Validators.required],
        password: ['', [Validators.minLength(4),  Validators.required]],
      });
  }



  async onSubmit(){

    this.submitted = true;
    this.loading = true;

    if(this.loginForm.invalid) return;
    let loginData :Login=Object.assign(this.loginForm.value);
    console.log("Above Generate Token");
    console.log(loginData);
    this.loginService.generateToken(loginData).pipe(delay(1300)).subscribe((data:any) =>{
      console.log("Inside Generate Token");
       //login...
       this.loginService.loginUser(data.token);
       this.loginService.getCurrentUser().subscribe((user:any)=>{
        //  return;
         console.log("Inside Current User");
         console.log(user);
           this.loginService.setUser(user);
           let authorities = this.loginService.getLoginUserRole();
          console.log(authorities);
           if( authorities.includes("ROLE_USER") || authorities.includes("ROLE_ADMIN") || authorities.includes("ROLE_SUPER_ADMIN") ){
            console.log("Inside if/else for roles based   login success");
            this.loading = false;
            this.toastr.success('You are now authenticated','success');
            this.toastr.success('You are now authenticated','Success', { positionClass:'toast-custom' })
            this.router.navigate(['dashboard']);
            this.loginService.loginStatusSubject.next(true);

           }else{ 
             this.loading = false;
             this.loginService.logout();
           }
         }
       );
     },
     (error) => {
      this.loading = false;
       console.log('Error !');
       console.log(error);
       this.toastr.error('Invalid Credentials');
     })
  }





  initButtonRippleEffect(){

    var createRipple = function(e){

      const button = e.currentTarget;
      let x = e.clientX - e.target.getBoundingClientRect().left;
      let y = e.clientY - e.target.getBoundingClientRect().top;
       // Create span element
       let ripple = document.createElement("span");
       // Position the span element
       ripple.style.cssText = "position: absolute; background: #fff; transform: translate(-50%, -50%); pointer-events: none; border-radius: 50%; animation: animate 0.8s linear infinite;";
       ripple.style.left = `${x}px`;
       ripple.style.top = `${y}px`;
       // Add span to the button
       button.appendChild(ripple);
       // Remove span after 0.3s
        setTimeout(() => {
              ripple.remove();
        }, 800);

   }

   const elements = document.querySelectorAll('.btn-ripple') as any as Array<HTMLElement>
   elements.forEach(element => {
    element.addEventListener('click', function(e){
      createRipple(e);
    });
   });


  }


}
