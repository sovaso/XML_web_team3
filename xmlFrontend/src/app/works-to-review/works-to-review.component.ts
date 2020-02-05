import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import {ScientificWorkService} from '../services/scientific-work.service';
import { ReviewFormComponent } from '../review-form/review-form.component';
import { AbstractDto } from '../dto/Abstract.dto';
import {AuthorDto} from 'src/app/dto/Author.dto';
@Component({
  selector: 'app-works-to-review.',
  templateUrl: './works-to-review.component.html',
  styleUrls: ['./works-to-review.component.css']
})
export class WorksToReviewComponent implements OnInit {

  worksToReview: ScientificWorkDto[]=[];

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

    /*
    this.scientificWorkService.findAllWorksToReview().subscribe(
    //   work => (this.scientificWorks = work)
    );
    */

  }

  open(work){

    /*
    this.scientificWorkService.getById(e.id).subscribe(data => {
      this.modalRef = this.modalService.open(OpenScientificWork);
      //this.modalRef.componentInstance.event = data;
    });
    */
  }

  openModal(){
    /*
    const modalRef = this.modalService.open(ReviewFormComponent);
    let workDto : ScientificWorkDto;
    let keywordss: string[]=[];
    keywordss.push('key1', 'key2');
    let abstract : AbstractDto ={
      purpose: 'purpose',
      design: 'design',
      findings: 'findings',
      limitations: 'limitations',
      originality: 'originality',
      type: 'type',
      keywords: keywordss

    };

    let authors: AuthorDto[]=[];
    let paragpraphs: string[]=[];
    let reference: string[]=[];

    let author : AuthorDto = {
      name: 'nameOfAuthor',
      surname: 'surnameOfAuthor',
      universityName: 'universityNameOfAuthor',
      universityAddress: 'universityAddressOfAuthor'
    };


    workDto={
      scientificWorkId: 'nekiScientificWorkId',
      headertDTO: null,
      title: 'title',
      authorsDTO: this.authors,
      abstractDTO: this.abstract,
      paragraphs:this.paragpraphs,
      referenceDTO: this.references,
      comments: [],
      status: 'submitted'
    };
    modalRef.componentInstance.scientificWork = workDto;
    */
  }

  addReviewer(work){

  }

  reject(work){
    this.scientificWorkService.rejectWork(work.scientificWorkId).subscribe(
      result => (console.log(result))
    )
  }

  accept(work){
    this.scientificWorkService.acceptWork(work.scientificWorkId).subscribe(
      result => (console.log(result))
    )
  }

}
