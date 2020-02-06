import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { ReviewDTO } from '../dto/Review.dto';
import { ScientificWorkService } from '../services/scientific-work.service';
import { CommentDTO } from '../dto/Comment.dto';

@Component({
  selector: 'app-edit-work',
  templateUrl: './edit-work.component.html',
  styleUrls: ['./edit-work.component.css']
})
export class EditWorkComponent implements OnInit {

  public scientificWork : ScientificWorkDto;
  public reviewDto : ReviewDTO[]=[];

  public theReview : ReviewDTO;

  public comments : CommentDTO[]=[];

  constructor(public scientificWorkService : ScientificWorkService) { }

  ngOnInit() {
    console.log('COMMENTS SIZE');
    console.log(this.scientificWork.comments.length);
    console.log('scientific work paragrafs-------------------------');
    console.log(this.scientificWork.paragraphs.length);
    this.scientificWorkService.getReviewsByScientificWorkId(this.scientificWork.scientificWorkId).subscribe(
      res => {
        this.reviewDto = res;
        console.log('Result of get reviews by scientific work id');
        console.log(this.reviewDto.length);
        this.theReview = this.reviewDto[0];
        console.log("The review");
        console.log(this.theReview.comments.length);
        this.comments=this.theReview.comments;
      
      }
    )

  }
  submitWork(){
    console.log('submit work clicked');
    this.scientificWorkService.updateScientificWork(this.scientificWork).subscribe(res=>{
      console.log(res);
    })
  }

}
