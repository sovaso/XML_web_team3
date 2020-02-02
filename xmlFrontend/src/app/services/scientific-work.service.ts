import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ScientificWorkDto } from '../dto/ScientificWork.dto';
import {MessageDto} from '../dto/MessageDto.dto'


@Injectable({
  providedIn: "root"
})
export class ScientificWorkService {

  constructor(private http: HttpClient) { }

  createScientificWork(scientificWorkDto: ScientificWorkDto): Observable<MessageDto> {
    return this.http.post<MessageDto>(`http://localhost:8000/scientificWork/create`, scientificWorkDto);
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
