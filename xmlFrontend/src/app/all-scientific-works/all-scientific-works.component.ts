import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import { NgbModalOptions, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';

@Component({
  selector: 'app-all-scientific-works',
  templateUrl: './all-scientific-works.component.html',
  styleUrls: ['./all-scientific-works.component.css']
})
export class AllScientificWorksComponent implements OnInit {

  scientificWorks: ScientificWorkDto[] = [];
  
  activeTab: String;

  field: string='';

  loggedUser: CurrentUser;

  message: String = '';

  type = '';

  modalOption: NgbModalOptions = {};

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
