import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { ScientificWorkService } from '../services/scientific-work.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EditWorkComponent } from '../edit-work/edit-work.component';

@Component({
  selector: 'app-for-revision',
  templateUrl: './for-revision.component.html',
  styleUrls: ['./for-revision.component.css']
})
export class ForRevisionComponent implements OnInit {

  worksForRevision: ScientificWorkDto[]=[];

  constructor(private modalService: NgbModal, public scientificWorkService: ScientificWorkService) { }

  ngOnInit() {
    this.scientificWorkService.findAllForRevision().subscribe(
      work => {
        console.log('For revision sizeeeee: '+work.length);
        this.worksForRevision = work;
        console.log('FOR REVISION SIZE');
        console.log(this.worksForRevision.length);
      }

    );
  }

  editWork(work){
    console.log('edit work called');
    console.log(work.scientificWorkId);
    const modalRef = this.modalService.open(EditWorkComponent);
    modalRef.componentInstance.scientificWork = work;
  }

  rejectWork(work){
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



}
