import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CommentDTO } from '../dto/Comment.dto';
import { ReviewDTO } from '../dto/Review.dto';
import { GradeDTO } from '../dto/Grade.dto';
import { ScientificWorkService } from '../services/scientific-work.service';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  public scientificWork : ScientificWorkDto;

  public reviewDto : ReviewDTO;

  comments : Array<CommentDTO> = [];

  uniqueness : number = 5;

  keywords : number = 5;

  abstract : number = 5;

  conclusion : number = 5;

  experiments : number = 5;

  layout : number = 5;

  formality : number = 5;

  generalGrade : number = 5;

  references : number = 5;

  headings : number = 5;

  summaryContent : string = '';

  constructor(private scientificWorkService: ScientificWorkService) { }

  ngOnInit() {
    console.log('REVIEW COMPONENT');
    console.log(this.scientificWork.title);
  }

  

  addComment(i){
    console.log('Adding comment for paragraph');
    console.log(i);
    var commentValue : string = (document.getElementById(i) as HTMLInputElement).value
    console.log(commentValue);
    let comment : CommentDTO = {
      value: commentValue,
      refId: i 
    }
    this.comments.push(comment);
/*
    comments : Array<CommentDTO>;
    grades : GradeDTO;
    workflowId : string;
    summaryComment : string;
    reviewedId : string;
    */
  }
  submitReview(){
    
    console.log('submit review called');

    let gradeDto : GradeDTO= {
      uniqueness: this.uniqueness,
      keywords: this.keywords,
      abstract: this.abstract,
      conclusions: this.conclusion,
      experiments: this.experiments,
      layout: this.layout,
      formality: this.formality,
      generalGrade: this.generalGrade,
      references: this.references,
      headings: this.headings
    }

    let reviewDto : ReviewDTO = {
      comments: this.comments,
      grades: gradeDto,
      scientificWorkId: this.scientificWork.scientificWorkId,
      summaryComment: this.summaryContent,
      reviewedId: ''
    }

    this.scientificWorkService.sendReview(reviewDto).subscribe(res => {
      console.log(res);
    });
    
  }
}
