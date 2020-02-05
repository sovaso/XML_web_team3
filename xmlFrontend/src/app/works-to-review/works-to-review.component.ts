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

    
    this.scientificWorkService.findAllWorksToReview().subscribe(
      work => (this.worksToReview = work)
    );

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
    console.log('*******************');
    console.log('*******************');
    console.log('*******************');
    console.log('*******************');
    console.log('Scientific work id');
    console.log(work.scientificWorkId);
    this.scientificWorkService.rejectWork(work.scientificWorkId).subscribe(
      created => {
        if(created==true){
          alert("SENT EMAIL");
          
        }else{
          alert("SENT EMAIL NOT");
        }
        location.reload();
    });

  }

  accept(work){
    console.log('Accept iz works to review comoponente');
    console.log(work.scientificWorkId);
    
    this.scientificWorkService.acceptWork(work.scientificWorkId).subscribe(
      result => {
        console.log('Rezultat iz accept iz komponente');
        console.log(result);
        location.reload();
      }
    )
    
  }

  makeAReview(work){
    console.log('Make a review called');
    console.log(work.scientificWorkId);
    const modalRef = this.modalService.open(ReviewFormComponent);
  }

}
