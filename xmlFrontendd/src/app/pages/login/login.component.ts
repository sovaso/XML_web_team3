import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService } from 'src/app/security/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user;

  public wrongUsernameOrPass:boolean;
  public noInput:boolean;
  public needToValidate:boolean;
  message: String = '';
  type = '';
  constructor(private authenticationService:AuthenticationService,
              private router: Router) {
    this.user = {};
    this.wrongUsernameOrPass = false;
    this.noInput=false;
   }

   ngOnInit() {
  }
  
   login():void{
    console.log(this.user.username);
    console.log(this.user.password);
    if (this.user.username !=undefined && this.user.password != undefined && this.user.username!="" && this.user.password!=""){

    
    this.authenticationService.login(this.user.username, this.user.password).subscribe(
      (loggedIn) => {
        console.log("logged in");
        location.reload();
      }
    ,
    (err:Error) => {
        this.message="Wrong password or username.";
        this.type = 'danger';
    });

  }else {
    this.message="Please enter username and password.";
    this.type = 'danger';
  }
  }


}
