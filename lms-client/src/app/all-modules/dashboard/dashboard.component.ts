import { Component, OnInit, HostListener, NgZone } from "@angular/core";
import { Router, Event, NavigationEnd } from "@angular/router";
import { LoginService } from "src/app/login/services/login.service";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"],
})
@HostListener("window: resize", ["$event"])
export class DashboardComponent implements OnInit {
  public innerHeight: any;

  getScreenHeight() {
    this.innerHeight = window.innerHeight + "px";
  }

  constructor(private ngZone: NgZone,private loginService: LoginService, private router: Router) {
    window.onresize = (e) => {
      this.ngZone.run(() => {
        this.innerHeight = window.innerHeight + "px";
      });
    };
    this.getScreenHeight();
  }

  ngOnInit() {
    let authorities = this.loginService.getLoginUserRole();
    console.log("inside of ng onInit of dashboard component");
    console.log(authorities);
    if(authorities.includes("ROLE_ADMIN")){
      this.router.navigateByUrl("/dashboard/admin");
    }
    if(authorities.includes("ROLE_USER")){
      this.router.navigateByUrl("/dashboard/admin");
    }
    if(authorities.includes("ROLE_SUPER_ADMIN")){
      this.router.navigateByUrl("/dashboard/error");
    }  
  }

  onResize(event) {
    this.innerHeight = event.target.innerHeight + "px";
  }
}
