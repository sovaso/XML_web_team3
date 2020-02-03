import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { ScientificWorkService } from './scientific-work.service';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public status : boolean = false;
  private worksSource = new BehaviorSubject<ScientificWorkDto[]>([]);
  works = this.worksSource.asObservable();

  constructor(scientificWorksService : ScientificWorkService) { }

  updateWorks() {
    /*
    this.scientificWorksService.getAll().subscribe(data => {
      this.worksSource.next(data);
    });
    */
  }

  updateAll() {
    this.updateWorks();
  }

  searchWork(field : string) {
    /*
    this.scientificWorksService.search(field).subscribe(data => {
      this.worksSource.next(data);
    }, (err : Error)=>{
     console.log('error');
     this.status=false;
    }
      
    );
    */
  }
}
