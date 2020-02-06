import { Component, OnInit } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { RegisterUserComponent } from '../pages/register-user/register-user.component';
import { RegisterEditorComponent } from '../register-editor/register-editor.component';
import { RegisterReviewerComponent } from '../register-reviewer/register-reviewer.component';
import { CurrentUser } from '../model/currentUser.model';
import { CanActivateAuthGuard } from '../security/can-acitvate-auth.guard';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css', './general.scss']
})
export class MenuBarComponent implements OnInit {

  currentUser : CurrentUser;
  constructor(private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
    console.log('ng on init called from menu bar');
    this.currentUser = JSON.parse(
      localStorage.getItem('currentUser'));
    console.log(this.currentUser);
    //uzmemo sa stranice sve elemente koje treba da sakrijemo 
    var addScientificWork: HTMLElement = document.getElementById('addScientificWork');
    var myWorks: HTMLElement = document.getElementById('myWorks');
    var worksToReview: HTMLElement = document.getElementById('worksToReview');
    var unreviewedWorks: HTMLElement = document.getElementById('unreviewedWorks');
    var reviewedWorks: HTMLElement = document.getElementById('reviewedWorks');
    var registerEditor: HTMLElement = document.getElementById('registerEditor');
    var registerReviewer: HTMLElement = document.getElementById('registerReviewer');
    var allScientificWorks : HTMLElement = document.getElementById('allScientificWorks');
    var forRevision : HTMLElement = document.getElementById('forRevision');
    var worksToReview : HTMLElement = document.getElementById('worksToReview');

    if (this.currentUser != null) {
      if (this.currentUser.userRoleName === 'ROLE_UNAUTHORIZED') {
        console.log('11111111111111');
        allScientificWorks.hidden = false;
        addScientificWork.hidden = false;
        myWorks.hidden = true;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
        worksToReview.hidden=true;
      
      } else if (this.currentUser.userRoleName === 'ROLE_AUTHOR') {
        console.log('AUTHOR LOGGED IN');
        allScientificWorks.hidden = false;
        addScientificWork.hidden = false;
        myWorks.hidden = false;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
        forRevision.hidden = false;
        worksToReview.hidden=true;
      
    } else if (this.currentUser.userRoleName === 'ROLE_REVIEWER'){
        console.log('REVIEWER LOGGED IN');
        allScientificWorks.hidden = false;
        addScientificWork.hidden = false;
        myWorks.hidden = false;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
        forRevision.hidden = true;
        worksToReview.hidden=false;
    }
    else if (this.currentUser.userRoleName === 'ROLE_EDITOR'){
      console.log('EDITOR LOGGED IN');
      allScientificWorks.hidden = false;
      addScientificWork.hidden = false;
      myWorks.hidden = false;
      unreviewedWorks.hidden = false;
      reviewedWorks.hidden = true;
      registerEditor.hidden = false;
      registerReviewer.hidden = false;
      forRevision.hidden = true;
      worksToReview.hidden=false;
    }
  }else {
    
      console.log('NO ONE LOGGED IN');
      allScientificWorks.hidden = false;
      addScientificWork.hidden = true;
      myWorks.hidden = true;
      unreviewedWorks.hidden = true;
      reviewedWorks.hidden = true;
      registerEditor.hidden = true;
      forRevision.hidden = true;
      worksToReview.hidden=true;
  }

  
  }

  registerEditor(){
    const modalRef = this.modalService.open(RegisterEditorComponent);
  }
  
  registerReviewer(){
    const modalRef = this.modalService.open(RegisterReviewerComponent);
  }

}
