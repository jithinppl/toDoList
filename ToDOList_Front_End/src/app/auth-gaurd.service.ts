import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurd implements CanActivate {

  constructor(private router:Router) { }
  canActivate(route: import("@angular/router").ActivatedRouteSnapshot, state: import("@angular/router").RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
   let token= localStorage.getItem('token');
   if(!token){
     this.router.navigate(['']);
     return false;
   }
   return true;
  }
}
