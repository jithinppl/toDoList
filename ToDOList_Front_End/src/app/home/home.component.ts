import { Component, OnInit, OnDestroy } from '@angular/core';
import { userview } from '../usermodel';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isinvalid:boolean;
  invaliddescription:string;
  user: userview;
  constructor(private userservice: UserService, private router: Router) {
    this.isinvalid=false;
    this.user = new userview();
    let token=localStorage.getItem('token');
    if(token){
      this.router.navigate(["/dashboard"]);
    }
  }
  

  ngOnInit(): void {
  }

  OnLoginClicked() {
    this.userservice.Loginuser(this.user).subscribe(res => {
      if (res) {
        this.router.navigate(["/dashboard"]);
      }
    }, error => {
      this.isinvalid=true;
      this.invaliddescription= error.error;
    });

  }

}
