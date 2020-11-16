import { Injectable } from '@angular/core';
import { userview } from './usermodel';
import { HttpClient } from '@angular/common/http';
import { Authres } from './Authres';
import { map } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  IsLoggedin:BehaviorSubject<boolean>;
  constructor(private http: HttpClient, private router: Router) {
    
    if(localStorage.getItem('token')){
      this.IsLoggedin=  new BehaviorSubject(true);
    }
    else{
      this.IsLoggedin=  new BehaviorSubject(false);
    }
   
  }
  
  Loginuser(user: userview) {
    var url = "http://localhost:8080/todolist-2.4.0/authentication";
    let Authresponse: Authres = new Authres();
    return this.http.post<Authres>(url, user).pipe(map(res => {
      if (res && res.token) {
        Authresponse.token = res.token;
        localStorage.setItem('token', res.token);
        this.IsLoggedin.next(true);
        return true;
      }
      return false;
    }));
  }
  Logoutuser() {
    localStorage.removeItem('token');
    this.IsLoggedin.next(false);
    this.router.navigate(['']);
  }
}
