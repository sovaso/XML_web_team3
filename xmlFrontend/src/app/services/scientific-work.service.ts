import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, BehaviorSubject } from "rxjs";
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import { CoverLetterDto } from '../dto/CoverLetter.dto';
import {MessageDto} from '../dto/MessageDto.dto'
import { map } from 'rxjs/operators';
import { IdDTO } from '../dto/IdDTO.dto';
import { SearchDto } from '../dto/Search.dto';
import {UserModelRegister} from '../model/user.model.register'
import {PickedReviewerDTO} from '../dto/PickedReviewer.dto'


@Injectable({
  providedIn: "root"
})
export class ScientificWorkService {

  
  private publishedWorksSource = new BehaviorSubject<ScientificWorkDto[]>([]);
  publishedWorks = this.publishedWorksSource.asObservable();

  constructor(private http: HttpClient) { }

  createScientificWork(scientificWorkDto: ScientificWorkDto): Observable<IdDTO> {
    return this.http.post<IdDTO>(`http://localhost:8000/scientificWork/create`, scientificWorkDto).pipe(
      map( (res: IdDTO) => {
        console.log(res.response);
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

  findAllWorksToReview(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/findAllForReviewing`);
  }

  getMyWorks(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/findAllForConcreteUser`);
  }

  acceptWork(scientificWorkId : string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8000/scientificWork/acceptScientificWork/${scientificWorkId}`);
  }

  rejectWork(scientificWorkId : string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8000/scientificWork/rejectScientificWork/${scientificWorkId}`);
  }

  getUnreviewed(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/findAllForReviewing`);
  }
  getReviewed(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/getReviewed`);
  }

  searchAuthorized(searchDto : SearchDto): Observable<ScientificWorkDto[]> {
    return this.http.post<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/searchAuthorized`, searchDto);
  }

  searchMyWorks(searchDto : SearchDto): Observable<ScientificWorkDto[]> {
    return this.http.post<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/searchMyWorks`, searchDto);
  }

  searchUnauthorized(searchDto : SearchDto): Observable<ScientificWorkDto[]> {
    return this.http.post<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/searchUnauthorized`, searchDto);
  }

  deleteScientificWork(scientificWorkId : string): Observable<boolean> {
    let res : Observable<boolean>;
    try{
      res = this.http.get<boolean>(`http://localhost:8000/scientificWork/withdrawScientificWork/${scientificWorkId}`);
  }catch(e){
    console.log('uslo u catch u servicu');
    return null;
  }

    } 
  createCoverLetter(coverLetterDto: CoverLetterDto): Observable<Boolean> {
    console.log('create cover letter in service called');
    return this.http.post<CoverLetterDto>(`http://localhost:8000/coverLetter/create`, coverLetterDto).pipe(
      map( (res: any) => {
          return res;
      })  );
    //console.log('create scientific work service called');
   // return this.http.post<Boolean>(`http://localhost:8000/scientificWork/create`, scientificWorkDto);
  }

  

  reject(id: String): Observable<Boolean> {
    console.log('create cover letter in service called');
    return this.http.put<Boolean>(`http://localhost:8000/scientificWork/rejectScientificWork`,id).pipe(
      map( (res: any) => {
          return res;
      })  );
  }

  getAllForEditor(): Observable<ScientificWorkDto[]> {
    return this.http.get<ScientificWorkDto[]>(`http://localhost:8000/scientificWork/getAllForEditor`);
  }

  getAllReviewers(): Observable<UserModelRegister[]> {
    return this.http.get<UserModelRegister[]>(`http://localhost:8000/user/getAllReviewers`);
  }

  pickedReviewer(pickedReviewerDTO: PickedReviewerDTO): Observable<Boolean> {
    console.log('create cover letter in service called');
    return this.http.post<CoverLetterDto>(`http://localhost:8000/scientificWork/pickedReviewer`, pickedReviewerDTO).pipe(
      map( (res: any) => {
          return res;
      })  );
    //console.log('create scientific work service called');
   // return this.http.post<Boolean>(`http://localhost:8000/scientificWork/create`, scientificWorkDto);
  }

  

  getById(idDto: IdDTO): Observable<IdDTO> {
    console.log('create cover letter in service called');
    return this.http.get<IdDTO>(`http://localhost:8000/scientificWork/getByIdHTML/${idDto.response}`).pipe(
      map( (res: any) => {
          return res;
      })  );


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
