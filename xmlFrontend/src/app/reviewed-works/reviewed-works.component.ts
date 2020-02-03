import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ScientificWorkService } from '../services/scientific-work.service';

@Component({
  selector: 'app-reviewed-works',
  templateUrl: './reviewed-works.component.html',
  styleUrls: ['./reviewed-works.component.css']
})
export class ReviewedWorksComponent implements OnInit {

  reviewedWorks: ScientificWorkDto[]=[];
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

    this.scientificWorkService.getReviewed().subscribe(
    //   work => (this.scientificWorks = work)
    );

  }
}
