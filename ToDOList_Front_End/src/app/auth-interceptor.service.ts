import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor{

  constructor() { }
  intercept(req: import("@angular/common/http").HttpRequest<any>, next: import("@angular/common/http").HttpHandler): import("rxjs").Observable<import("@angular/common/http").HttpEvent<any>> {
    const idToken = localStorage.getItem("token");

    if (idToken) {
        const cloned = req.clone({
            headers: req.headers.set("Authorization",
                "Bearer " + idToken)
        });

        return next.handle(cloned);
    }
    else {
        return next.handle(req);
    }
  }
}
