import {Injectable, Component} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from "rxjs/operators";
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { UserModel } from 'src/app/model/user.model';
import { Mail } from 'src/app/model/mail.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RegisterUserService } from './register-user.service';
import { Router } from '@angular/router';
import { UserModelRegister } from 'src/app/model/user.model.register';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})


@Injectable({providedIn: 'root'})
export class RegisterUserComponent {

  public user;
  public success:boolean;
  public noInput:boolean;
  message: String = '';
  type = '';
  public repeatedPassword : String = '';
  constructor(public activeModal: NgbActiveModal, private registerUserService: RegisterUserService, private router: Router) { 
    this.user = {};
    this.success=false;
    this.noInput=false;
  }

  ngOnInit() {
  }

   validateEmail(email) 
{
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}
  register():void{
    console.log('register function called');
    this.user.enabled = false;
    if (this.user.name != undefined && this.user.surname != undefined && this.user.email != undefined &&
      this.user.username != undefined && this.user.password!=undefined){

      console.log("Jedan");
      if (this.user.password==this.repeatedPassword){
        console.log("Dva");
        if (this.validateEmail(this.user.email) == true){
          console.log("Tri");
          console.log('validan je email');
          this.user.role = "ROLE_AUTHOR";
          this.registerUserService.register(this.user).subscribe(
            (registered:boolean) => {
              console.log("result of registered");
              console.log(registered);
              if(registered === true){
                console.log("is registered in");
                console.log(registered);
                this.message = "Successful registration, congratulations!",
                "success";
                this.type = 'success';
                var username = this.user.username;
              }else {
                console.log('treba da se upise da username vec postoji');
                this.user.username='';
                this.message = 'Username already exist.';
                this.type = 'danger';
              }
            }
          ,
            (err:Error) => {
                this.user.username='';
                  this.message = 'Username already exist.';
                  this.type = 'danger';
                console.log(err);
            
            });
        }else {
          this.message = 'Invalid email.';
          this.type = 'danger';
        }
        
      }else {
        
        this.message = 'Passwords must match.';
        this.type = 'danger';
      }
      
  }else {
   
    this.message = 'Please fill all fields.';
    this.type = 'danger';
  }
  }
}


