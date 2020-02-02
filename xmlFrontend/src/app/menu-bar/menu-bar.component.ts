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
    this.currentUser = JSON.parse(
      localStorage.getItem('currentUser'));
    //uzmemo sa stranice sve elemente koje treba da sakrijemo 
    var scientificWorks: HTMLElement = document.getElementById('scientificWorks');
    var myWorks: HTMLElement = document.getElementById('myWorks');
    var worksToReview: HTMLElement = document.getElementById('worksToReview');
    var unreviewedWorks: HTMLElement = document.getElementById('unreviewedWorks');
    var reviewedWorks: HTMLElement = document.getElementById('reviewedWorks');
    var registerEditor: HTMLElement = document.getElementById('registerEditor');
    var registerReviewer: HTMLElement = document.getElementById('registerReviewer');
    
    if (this.currentUser != null) {
      if (this.currentUser.userRoleName === 'ROLE_UNAUTHORIZED') {
        console.log('11111111111111');
        scientificWorks.hidden = false;
        myWorks.hidden = true;
        worksToReview.hidden = true;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
      
      } else if (this.currentUser.userRoleName === 'ROLE_AUTHOR') {
        console.log('AUTHOR LOGGED IN');
        scientificWorks.hidden = false;
        myWorks.hidden = false;
        worksToReview.hidden = true;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
      
    } else if (this.currentUser.userRoleName === 'ROLE_REVIEWER'){
        console.log('REVIEWER LOGGED IN');
        scientificWorks.hidden = false;
        myWorks.hidden = false;
        worksToReview.hidden = false;
        unreviewedWorks.hidden = true;
        reviewedWorks.hidden = true;
        registerEditor.hidden = true;
        registerReviewer.hidden = true;
    }
    else if (this.currentUser.userRoleName === 'ROLE_EDITOR'){
      console.log('EDITOR LOGGED IN');
      scientificWorks.hidden = false;
      myWorks.hidden = false;
      worksToReview.hidden = false;
      unreviewedWorks.hidden = true;
      reviewedWorks.hidden = false;
      registerEditor.hidden = false;
      registerReviewer.hidden = false;
    }
  }else {
    
      console.log('NO ONE LOGGED IN');
      scientificWorks.hidden = false;
      myWorks.hidden = true;
      worksToReview.hidden = true;
      unreviewedWorks.hidden = true;
      reviewedWorks.hidden = true;
      registerEditor.hidden = true;
      registerReviewer.hidden = true;
  }

  
  }
  

}
