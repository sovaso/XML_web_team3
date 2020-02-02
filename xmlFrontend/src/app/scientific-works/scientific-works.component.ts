import { Component, OnInit } from '@angular/core';
import {AbstractDto} from 'src/app/dto/Abstract.dto'
import {AuthorDto} from 'src/app/dto/Author.dto'
import {HeaderDto} from 'src/app/dto/Header.dto'
import {ReferenceDto} from 'src/app/dto/Reference.dto'
import {ScientificWorkDto} from 'src/app/dto/ScientificWork.dto'

@Component({
  selector: 'app-scientific-works',
  templateUrl: './scientific-works.component.html',
  styleUrls: ['./scientific-works.component.css']
})
export class ScientificWorksComponent implements OnInit {

  title: string='';
  authors: AuthorDto[]=[];
  abstract : AbstractDto;
  paragpraphs: string='';
  reference: string[]=[]
  numOfAuthors: number=0;
  numOfKeywords: number=0;
  numOfParagraph: number=0;
  numOfReferences: number=0;
  allPublished: ScientificWorkDto[]=[]
  coverLetter: string='';

  constructor() { }

  ngOnInit() {
    this.abstract={
      purpose:'',
      design:'',
      findings:'',
      limitations:'',
      originality: '',
      type: '',
      keywords: []
    }
  }

  addParagraph(){

  }

  addKeyword(){
    
  }

  addAuthor(){
    
  }

}
