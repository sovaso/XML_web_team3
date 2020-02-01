import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from "rxjs/operators";
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { UserModel } from 'src/app/model/user.model';
import { Mail } from 'src/app/model/mail.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { UserModelRegister } from 'src/app/model/user.model.register';
import { RegisterUserService } from '../pages/register-user/register-user.service';


@Injectable({providedIn: 'root'})
export class RegisterEditorComponent {

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
    this.user.enabled = false;
    if (this.user.name != undefined && this.user.surname != undefined && this.user.email != undefined &&
      this.user.username != undefined && this.user.password!=undefined){

      if (this.user.password==this.repeatedPassword){
        if (this.validateEmail(this.user.email) == true){
          console.log('validan je email');
          this.user.role = "ROLE_EDITOR";
          this.registerUserService.register(this.user).subscribe(
            (registered:boolean) => {
              console.log("nestooo");
              if(registered){
                console.log("is registered in");
                this.message = "Successful registration, congratulations!",
                "success";
                this.type = 'success';
                var username = this.user.username;
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

