import { Component, OnInit, Input } from '@angular/core';


import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserModel } from '../model/user.model';
import { LoginComponent } from '../pages/login/login.component';

import { RegisterUserComponent } from '../pages/register-user/register-user.component';
import { AuthenticationService } from '../security/authentication.service';
import { CurrentUser } from '../model/currentUser.model';
import { RegisterEditorComponent } from '../register-editor/register-editor.component';
import { RegisterReviewerComponent } from '../register-reviewer/register-reviewer.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css', './general.scss']
})
export class HeaderComponent implements OnInit {

  loggedUser: CurrentUser;
  username: string;


  //moze da se user povuce iz storage-a; uloga i username, ne ceo user, jer se cuva token 
  constructor(private modalService: NgbModal, private AuthenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(
      localStorage.getItem('currentUser'));

    var login: HTMLElement = document.getElementById('loginButton');
    var logout: HTMLElement = document.getElementById('logoutItem');
    var register: HTMLElement = document.getElementById('registerItem');
    var notRegistrated: HTMLElement = document.getElementById('notRegistrated');
    var registrated: HTMLElement = document.getElementById('registrated');


    if (this.loggedUser === null) {
      //alert('niko nije ulogovan');
      notRegistrated.hidden = false;
      registrated.hidden = true;

    } else {
      //logout.hidden = false;
      //edit.hidden = false;
      //login.hidden = true;

      notRegistrated.hidden = true;
      registrated.hidden = false;
      if (this.loggedUser.userRoleName === "ROLE_EDITOR") {
        login.hidden = true;
        register.hidden = false;
      } else {
        register.hidden = true;
        login.hidden = true;
      }

    }

  }

  open() {
    console.log('login called');
    const modalRef = this.modalService.open(LoginComponent);
    
  }
  openReg() {

    const modalRef = this.modalService.open(RegisterUserComponent);
  }



  logout() {
    this.AuthenticationService.logout();
    location.reload();
  }

  registerEditor(){
    const modalRef = this.modalService.open(RegisterEditorComponent);
  }

  registerReviewer(){
    const modalRef = this.modalService.open(RegisterReviewerComponent);
  }



}




