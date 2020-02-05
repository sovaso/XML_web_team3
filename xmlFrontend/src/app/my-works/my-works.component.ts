import { Component, OnInit,ViewChild,ElementRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import { ScientificWorkService } from '../services/scientific-work.service';
import * as jsPDF from 'jspdf';
import { IdDTO } from '../dto/IdDTO.dto';

@Component({
  selector: 'app-my-works',
  templateUrl: './my-works.component.html',
  styleUrls: ['./my-works.component.css']
})
export class MyWorksComponent implements OnInit {

  //@ViewChild('content', {static: false}) content: ElementRef;
  public getPdf(){
    let doc=new jsPDF();
    let specialEventHandlers={
      '#editor': function(element,renderer){
        return true;
      }
    };

    //let content=this.content.nativeElement;
    doc.fromHTML(this.htmlEl,15,15,{
      'width':190,
      'elementHandlers': specialEventHandlers
    });
    doc.save('test.pdf');


  }

  htmlEl: string='';
  workForDownload: IdDTO;

  myWorks: ScientificWorkDto[] = [];
  
  activeTab: String;

  field: string='';

  loggedUser: CurrentUser;

  message: String = '';

  type = '';

  //modalOption: NgbModalOptions = {};

  modalRef : any;

  constructor(private modalService: NgbModal, public sharedService: SharedService, public scietificWorkService : ScientificWorkService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
    this.scietificWorkService.getMyWorks().subscribe(works => (this.myWorks = works)); 
   
  }

  search(){
    console.log('My works size');
    console.log(this.myWorks.length);
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

  reject(work){
    
    this.scietificWorkService.reject(work.scientificWorkId).subscribe(
      created => {
        if(created==true){
          alert("SENT EMAIL");
        }else{
          alert("SENT EMAIL NOT");
        }
    });

  }

  download(work){
    this.workForDownload={
      response: work.scientificWorkId
    };
    
    this.scietificWorkService.getById(this.workForDownload).subscribe(
      created => {
        this.htmlEl=created.response;
        alert(this.htmlEl);
        this.getPdf();
    });

  }

  
}
