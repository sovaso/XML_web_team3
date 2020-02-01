import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from "rxjs/operators";
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { UserModel } from 'src/app/model/user.model';
import { Mail } from 'src/app/model/mail.model';
import { UserModelRegister } from 'src/app/model/user.model.register';

@Injectable({providedIn: 'root'})
export class RegisterUserService {

  apiUrl: string;

  constructor(
    private http: HttpClient
  ) {
    
   
  }
  register = (data: UserModelRegister): Observable<boolean> => {

    var user = {};
    var loggedUser = JSON.parse(
      localStorage.getItem('currentUser'));
  
      this.apiUrl = "http://localhost:8000/user/register";
   
      return this.http.post<Message>(this.apiUrl, data).pipe(
        map( (res: any) => {
            return res;
        })  );
  }


 
}


