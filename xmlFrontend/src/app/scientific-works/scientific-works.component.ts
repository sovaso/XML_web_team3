import { Component, OnInit } from '@angular/core';
import {AbstractDto} from 'src/app/dto/Abstract.dto';
import {AuthorDto} from 'src/app/dto/Author.dto';
import {HeaderDto} from 'src/app/dto/Header.dto';
import {ReferenceDto} from 'src/app/dto/Reference.dto';
import {ScientificWorkDto} from 'src/app/dto/ScientificWork.dto';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { AlertBoxComponent } from 'src/app/alert-box/alert-box.component';
import { ScientificWorkService } from '../services/scientific-work.service';

@Component({
  selector: 'app-scientific-works',
  templateUrl: './scientific-works.component.html',
  styleUrls: ['./scientific-works.component.css']
})

export class ScientificWorksComponent implements OnInit {

  title: string='';
  authorName: string='';
  authorUniversityName: string='';
  authorUniversityAddress: string='';
  authorParagraph: string='';

  purpose: string='';
  design: string='';
  findings:string='';
  limitations:string='';
  originality: string='';
  type: string='';


  authors: AuthorDto[]=[];
  abstract : AbstractDto;
  paragpraphs: string[]=[];
  reference: string[]=[];
  allPublished: ScientificWorkDto[]=[];
  coverLetter: string='';
  keywords: string[]=[];
  keyword: string='';
  scientificWork: ScientificWorkDto;

  constructor(private modalService: NgbModal,private scientificWorkService: ScientificWorkService) { }

  ngOnInit() {

  }

  addParagraph(){
    this.paragpraphs.push(this.authorParagraph);
    this.authorParagraph='';
    
  }

  addKeyword(){
    this.keywords.push(this.keyword);
    this.keyword='';
    
  }

  addAuthor(){
    this.authors.push({name: this.authorName,
    universityName: this.authorUniversityName,
    universityAddress: this.authorUniversityAddress});
    this.authorName='';
    this.authorUniversityAddress='';
    this.authorUniversityName='';
  }

  addScientificWork(){
    this.scientificWork={
      headertDTO: null,
      title: this.title,
      authorsDTO: this.authors,
      paragraphs:this.paragpraphs,
      referenceDTO: null,
      comments: []
    };

    this.scientificWorkService.createScientificWork(this.scientificWork);
  }

}
