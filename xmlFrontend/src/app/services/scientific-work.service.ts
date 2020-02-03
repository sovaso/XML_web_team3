import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, BehaviorSubject } from "rxjs";
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CoverLetterDto } from '../dto/CoverLetter.dto';
import {MessageDto} from '../dto/MessageDto.dto'
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: "root"
})
export class ScientificWorkService {

  
  private publishedWorksSource = new BehaviorSubject<ScientificWorkDto[]>([]);
  publishedWorks = this.publishedWorksSource.asObservable();

  constructor(private http: HttpClient) { }

  

  createScientificWork(scientificWorkDto: ScientificWorkDto): Observable<string> {

    return this.http.post<string>(`http://localhost:8000/scientificWork/create`, scientificWorkDto).pipe(
      map( (res: any) => {
          return res;
      })  );
    //console.log('create scientific work service called');
   // return this.http.post<Boolean>(`http://localhost:8000/scientificWork/create`, scientificWorkDto);
  }

  getAllPublished(): Observable<ScientificWorkDto[]> {
    return this.http.get(`http://localhost:8000/scientificWork/getAllPublished`).pipe(
      map( (res: any) => {
          return res;
      })  );
  }

  getAccepted(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/getAllPublished`);
  }

  createCoverLetter(coverLetterDto: CoverLetterDto): Observable<Boolean> {

    return this.http.post<CoverLetterDto>(`http://localhost:8000/scientificWork/createCoverLetter`, coverLetterDto).pipe(
      map( (res: any) => {
          return res;
      })  );
    //console.log('create scientific work service called');
   // return this.http.post<Boolean>(`http://localhost:8000/scientificWork/create`, scientificWorkDto);
  }
  


//   getAll(): Observable<Location[]> {
//     return this.http.get<Location[]>(`/api/getAllLocations`);
//   }

//   getActive(): Observable<Location[]> {
//     return this.http.get<Location[]>(`/api/getActiveLocations`);
//   }

//   getById(id: String): Observable<Location> {
//     return this.http.get<Location>(
//       `/api/getLocation/${id}`
//     );
//   }

//   create(location: LocationDto): Observable<MessageDto> {
//     return this.http.post<MessageDto>(`/api/createLocation`, location);
//   }

//   update = (location: LocationDto): Observable<MessageDto> =>
//   this.http.post<MessageDto>(`/api/updateLocation`, location);

//   delete = (id: string): Observable<MessageDto> =>{
//     console.log('delete from service called');
//     console.log(id);
//     return this.http.delete<MessageDto>(`/api/deleteLocation/${id}`)
//   }

//   seeReport = (id: string): Observable<LocationReportDto> =>
//   this.http.get<LocationReportDto>(`/api/getLocationReport/${ id }`);

  

//   checkNameAndAddress = (name: string,address:string): Observable<MessageDto> =>
//   this.http.get<MessageDto>(`/api/checkIfNameAndAddressAvailable/${ name }/${ address }`);
  
  

  

  

  
}
