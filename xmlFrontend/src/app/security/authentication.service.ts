import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtUtilsService } from './jwt-utils.service';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly loginPath = "http://localhost:8000/auth/login";

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService) { }

  login(username: string, password: string) {
    console.log('login called');
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.loginPath, JSON.stringify({ username, password }), { headers }).pipe(
      map( ((res: any) => {
         console.log('result from login');
         console.log(res);
          console.log(res['accessToken']);
          console.log(res['userRoleName']);
          console.log("1");
          let token = res && res['jsonWebToken'];
          console.log('TOKEN');
          console.log(token);
          console.log("2");
          if (token) {
            console.log("3");
            localStorage.setItem('currentUser', JSON.stringify({
              userRoleName: res['userRoleName'],
              token: res['jsonWebToken']
            }));
            console.log("4");
          }
          
      })  
      ) 
      );
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    
    if (currentUser!==null){
      return currentUser['token'];
    }
  
    return "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    if (this.getToken() != '') return true;
    else return false;
  }

  getCurrentUser() {
    if (localStorage.currentUser) {
      return JSON.parse(localStorage.currentUser);
    }
    else {
      return undefined;
    }
  }
}