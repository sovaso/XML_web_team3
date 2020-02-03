import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import {ScientificWorkService} from '../services/scientific-work.service';

@Component({
  selector: 'app-unreviewed-works',
  templateUrl: './unreviewed-works.component.html',
  styleUrls: ['./unreviewed-works.component.css']
})
export class UnreviewedWorksComponent implements OnInit {
  unreviewedWorks: ScientificWorkDto[]=[];
  activeTab: String;

  field: string='';

  loggedUser: CurrentUser;

  message: String = '';

  type = '';

  //modalOption: NgbModalOptions = {};

  modalRef : any;

  constructor(private modalService: NgbModal, public scientificWorkService: ScientificWorkService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));

    this.scientificWorkService.getUnreviewed().subscribe(
    //   work => (this.scientificWorks = work)
    );

  }

  search(){
    console.log('search clicked');
  }

  reset(){
    console.log('reset clicked');
  }

  open(work){

    /*
    this.scientificWorkService.getById(e.id).subscribe(data => {
      this.modalRef = this.modalService.open(OpenScientificWork);
      //this.modalRef.componentInstance.event = data;
    });
    */
  }

  addReviewer(work){

  }

  reject(work){
    
  }

}
