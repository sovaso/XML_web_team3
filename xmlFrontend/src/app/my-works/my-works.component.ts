import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';

@Component({
  selector: 'app-my-works',
  templateUrl: './my-works.component.html',
  styleUrls: ['./my-works.component.css']
})
export class MyWorksComponent implements OnInit {

  scientificWorks: ScientificWorkDto[] = [];
  
  activeTab: String;

  field: string='';

  loggedUser: CurrentUser;

  message: String = '';

  type = '';

  //modalOption: NgbModalOptions = {};

  modalRef : any;

  constructor(private modalService: NgbModal, public sharedService: SharedService,/* public scietificWorkService : ScientificWorkService*/) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));

    this.sharedService.works.subscribe(
      work => (this.scientificWorks = work)
    );

    this.sharedService.works.subscribe(works => (this.scientificWorks = works));
   
    if (this.scientificWorks.length === 0) {
      this.sharedService.updateAll();
    }
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


}
