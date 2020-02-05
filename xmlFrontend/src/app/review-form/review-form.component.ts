import { Component, OnInit } from '@angular/core';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  public scientificWork : ScientificWorkDto;

  constructor() { }

  ngOnInit() {
  }

}
