import { Component, OnInit } from '@angular/core';
import {AbstractDto} from 'src/app/dto/Abstract.dto';
import {AuthorDto} from 'src/app/dto/Author.dto';
import {HeaderDto} from 'src/app/dto/Header.dto';
import {ReferenceDto} from 'src/app/dto/Reference.dto';
import {ScientificWorkDto} from 'src/app/dto/ScientificWork.dto';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { AlertBoxComponent } from 'src/app/alert-box/alert-box.component';

@Component({
  selector: 'app-scientific-works',
  templateUrl: './scientific-works.component.html',
  styleUrls: ['./scientific-works.component.css']
})

export class ScientificWorksComponent implements OnInit {

  title: string='';
  authors: AuthorDto[]=[];
  abstract : AbstractDto;
  paragpraphs: string[]=[];
  reference: string[]=[];
  numOfAuthors: number=0;
  numOfKeywords: number=0;
  numOfParagraph: number=0;
  numOfReferences: number=0;
  allPublished: ScientificWorkDto[]=[];
  coverLetter: string='';
  keywords: string[]=[];

  constructor(private modalService: NgbModal) { }

  ngOnInit() {
    this.abstract ={
    purpose:'',
    design:'',
    findings:'',
    limitations:'',
    originality: '',
    type: '',
    keywords: []
    };
  
    this.authors[0]={
      name: '',
      universityName:'',
      universityAddress:''
    };
    this.paragpraphs[0]='';
    this.keywords[0]='';


  }

  addParagraph(){
    if(this.paragpraphs[this.numOfParagraph]==''){
      const modalRef = this.modalService.open(AlertBoxComponent);
      modalRef.componentInstance.message="Paragraph cannot be empty!";
    }
    this.numOfParagraph+=1;
    this.paragpraphs[this.numOfParagraph]='';
    
  }

  addKeyword(){
    this.numOfKeywords+=1;
    this.keywords[this.numOfKeywords]='';
    
  }

  addAuthor(){
    this.numOfAuthors+=1;
    this.authors[this.numOfAuthors]={
      name: '',
      universityName: '',
      universityAddress: ''
    };
  }

  addScientificWork(){

  }

}
