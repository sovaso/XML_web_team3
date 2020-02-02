import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-scientific-works',
  templateUrl: './scientific-works.component.html',
  styleUrls: ['./scientific-works.component.css']
})
export class ScientificWorksComponent implements OnInit {

  title: string='';
  authors: AuthorDTO[]=[];
  abstract : AbstractDTO;
  paragpraphs: string='';
  reference: string[]=[]
  numOfAuthors: number=0;
  numOfKeywords: number=0;
  numOfParagraph: number=0;
  numOfReferences: number=0;
  allScientificWorks: ScientificWorkDTO[]=[]

  constructor() { }

  ngOnInit() {
    this.abstract={
      id:'',
      name:'',
      address:'',
      description:'',
      locationZone:[]
    }
  }

}
