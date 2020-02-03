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

  message: String = '';
  messageSuccess: String ='';

  constructor(private modalService: NgbModal,private scientificWorkService: ScientificWorkService) { }

  ngOnInit() {
    this.scientificWorkService.getAllPublished().subscribe(
      data => (this.publishedReferences = data)
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

  addScientificWork(){
    console.log('add scientific work in component called');



    if (this.title == '' || this.authors[0].name == "" || this.authors[0].surname == ""|| this.authors[0].universityName == ''
    || this.authors[0].universityAddress == '' || this.paragpraphs[0] == '' ||
    this.purpose == '' || this.design == '' || this.findings == '' || this.limitations == ''
    || this.originality == '' || this.type == '' || this.coverLetter==''
    ) {
      console.log('fields are not allowed to be empty');
      this.message = 'All fields must be filled.';
    }else {
      console.log('service should be called now');
      

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
        referenceDTO: [],
        comments: []
      };

      this.scientificWorkService.createScientificWork(this.scientificWork).subscribe(
        (created:string) => {
          this.sWId=created;
          console.log('Result of creation');
          console.log(created);
          
        }
      );
      //this.scientificWorkService.createScientificWork(this.scientificWork);
      if(this.sWId==""){
          this.messageSuccess='Scientific work cannot be created';
      }else{
        this.coverLetterDto={
          coverLetterId: null,
          scientificWorkId: this.sWId,
          text: this.coverLetter
  
        }
        this.scientificWorkService.createCoverLetter(this.coverLetterDto);


        this.messageSuccess = 'Scientific work successfully added.';
      }
    }
    
  }

}
