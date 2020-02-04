import { Component, OnInit } from '@angular/core';
import {AbstractDto} from 'src/app/dto/Abstract.dto';
import {AuthorDto} from 'src/app/dto/Author.dto';
import {HeaderDto} from 'src/app/dto/Header.dto';
import {ReferenceDto} from 'src/app/dto/Reference.dto';
import {CoverLetterDto} from 'src/app/dto/CoverLetter.dto';
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
  authorSurname: string = '';
  authorUniversityName: string='';
  authorUniversityAddress: string='';
  authorParagraph: string='';

  purpose: string='';
  design: string='';
  findings:string='';
  limitations:string='';
  originality: string='';
  type: string='';
  sWId='';

  authors: AuthorDto[]=[];
  abstract : AbstractDto;
  paragpraphs: string[]=[];
  reference: string[]=[];
  allPublished: ScientificWorkDto[]=[];
  coverLetter: string='';
  keywords: string[]=[];
  keyword: string='';
  scientificWork: ScientificWorkDto;
  publishedReferences: ScientificWorkDto[]=[];
  coverLetterDto: CoverLetterDto;

  message: string = '';
  messageSuccess: string ='';

  addedWorks : string[]=[];

  references: ReferenceDto[]=[];

  constructor(private modalService: NgbModal,private scientificWorkService: ScientificWorkService) { }

  ngOnInit() {
    this.scientificWorkService.getAllPublished().subscribe(
      data => (this.allPublished = data)
     

    );


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
      surname: this.authorSurname,
    universityName: this.authorUniversityName,
    universityAddress: this.authorUniversityAddress});
    this.authorName='';
    this.authorSurname='';
    this.authorUniversityAddress='';
    this.authorUniversityName='';
  }

  removeAuthor(author){
    for(let i=0;i<this.authors.length;i++){
      if(author.name==this.authors[i].name && author.surname==this.authors[i].surname && author.universityName==this.authors[i].universityName && author.universityAddress==this.authors[i].universityAddress){
        this.authors.splice(i,1);
      }
    }

  }

  removeParagraph(paragraph){
    for(let i=0;i<this.paragpraphs.length;i++){
      if(paragraph==this.paragpraphs[i]){
        this.paragpraphs.splice(i,1);
      }
    }

  }

  removeKeyword(keyword){
    for(let i=0;i<this.keywords.length;i++){
      if(keyword==this.keywords[i]){
        this.keywords.splice(i,1);
      }
    }

  }

  addWork(){
    console.log('add work called');
  }

  addScientificWork(){
    console.log('add scientific work in component called');
    console.log("ALL PUBLISHED DATA SIZE");
    console.log(this.allPublished.length);


    if (this.title == '' || this.authors[0].name == "" || this.authors[0].surname == ""|| this.authors[0].universityName == ''
    || this.authors[0].universityAddress == '' || this.paragpraphs[0] == '' ||
    this.purpose == '' || this.design == '' || this.findings == '' || this.limitations == ''
    || this.originality == '' || this.type == '' || this.coverLetter==''
    ) {
      console.log('fields are not allowed to be empty');
      this.message = 'All fields must be filled.';
    }else {
      console.log('service should be called now');

      for(let el of this.addedWorks){
        this.references.push({scientificWorkId: el});
      }
      

      this.abstract={
        purpose: this.purpose,
        design: this.design,
        findings: this.findings,
        limitations:this.limitations,
        originality: this.originality,
        type: this.type,
        keywords: this.keywords
  
      };
      this.scientificWork={
        scientificWorkId: null,
        headertDTO: null,
        title: this.title,
        authorsDTO: this.authors,
        abstractDTO: this.abstract,
        paragraphs:this.paragpraphs,
        referenceDTO: this.references,
        comments: [],
        status: ''
      };

      this.scientificWorkService.createScientificWork(this.scientificWork).subscribe(
        created => {
          this.sWId=created.response;
          console.log('Result of creation');
          console.log(created);
          if(this.sWId==""){
            this.messageSuccess='Scientific work cannot be created';
          }else{
            this.coverLetterDto={
              coverLetterId: null,
              scientificWorkId: this.sWId,
              text: this.coverLetter
             
      
            };
            console.log('Now creation of cover letter should be called');
            this.scientificWorkService.createCoverLetter(this.coverLetterDto).subscribe(res => {
              console.log('Result of creating cover letter');
              console.log(res);
            });;
          
    
            this.messageSuccess = 'Scientific work successfully added.';
          }
        }
      );
      //this.scientificWorkService.createScientificWork(this.scientificWork);
     
    }
    
  }

}
