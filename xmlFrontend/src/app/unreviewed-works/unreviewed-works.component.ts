import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SharedService } from '../services/shared.service';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CurrentUser } from '../model/currentUser.model';
import {ScientificWorkService} from '../services/scientific-work.service';
import { IdDTO } from '../dto/IdDTO.dto';
import {UserModelRegister} from '../model/user.model.register'
import { PickedReviewerDTO } from '../dto/PickedReviewer.dto';
import * as jsPDF from 'jspdf';

@Component({
  selector: 'app-unreviewed-works',
  templateUrl: './unreviewed-works.component.html',
  styleUrls: ['./unreviewed-works.component.css']
})
export class UnreviewedWorksComponent implements OnInit {
  public getPdf(){
    let doc=new jsPDF();
    let specialEventHandlers={
      '#editor': function(element,renderer){
        return true;
      }
    };

    //let content=this.content.nativeElement;
    doc.fromHTML(this.htmlEl.replace("text-align: center; font-weight: bold;","text-align: left; font-weight: bold;"),15,15,{
      'width':190,
      'elementHandlers': specialEventHandlers
    });
    doc.save('test.pdf');


  }

  htmlEl: string='';
  workForDownload: IdDTO;

  forReviewing: ScientificWorkDto[]=[];

  allReviewers: UserModelRegister[]=[];
  
  activeTab: String;

  field: string='';

  parametar: string='';

  loggedUser: CurrentUser;

  message: String = '';

  type = '';

  //modalOption: NgbModalOptions = {};

  modalRef : any;

  someText: string='';

  author: string='';

  title: string='';

  searchDto : any;

  addingReviewer: Boolean=false;

  workForReviewer: string="";

  addedReviewer: String="";

  pickedReviewerDTO: PickedReviewerDTO;

  constructor(private modalService: NgbModal, public sharedService: SharedService, public scietificWorkService : ScientificWorkService) { }

  ngOnInit() {
    this.loggedUser = JSON.parse(localStorage.getItem("currentUser"));
    this.scietificWorkService.getAllForEditor().subscribe(works => (this.forReviewing= works)); 
    this.scietificWorkService.getAllReviewers().subscribe(works=>(this.allReviewers=works));
   
  }

  printaj(){
    console.log("PRINTAJ "+this.parametar);
  }

  reset(){
    console.log('reset clicked');
    this.author = '';
    this.someText = '';
    this.title = '';
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
    this.addingReviewer=true;
    this.workForReviewer=work.scientificWorkId;

  }

  add(){
    this.addingReviewer=false;
    var e = (<HTMLInputElement>document.getElementById("reviewers")).value;
    var name = (<HTMLInputElement>document.getElementById("reviewers")).name;
    this.pickedReviewerDTO={
      editorUsername: "",
      reviewerUsername: e,
      scientificWorkId: name

    };

    console.log("SCIIENTI ",name);
    console.log("SCIIENTI ",e);

    this.scietificWorkService.pickedReviewer(this.pickedReviewerDTO).subscribe(
      res => {
        (console.log(res));
      }
    );
  }
  
  
  delete (work){
    console.log('delete called');
    console.log(work.scientificWorkId);

    try{
      this.scietificWorkService.deleteScientificWork(work.scientificWorkId).subscribe(
        res => {
          (console.log(res));
          location.reload();
        }
      );
    }catch(e){
      console.log('uslo u catch blok u my works componentu');
      location.reload();
    }
    
  }

  reject(work){
    
    this.scietificWorkService.reject(work.scientificWorkId).subscribe(
      created => {
        if(created==true){
          alert("SENT EMAIL");
          
        }else{
          alert("SENT EMAIL NOT");
        }
        location.reload();
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
