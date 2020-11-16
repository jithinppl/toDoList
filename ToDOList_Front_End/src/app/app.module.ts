import { AuthInterceptor } from './auth-interceptor.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService } from './user.service';
import { AuthGaurd } from './auth-gaurd.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path:'', component: HomeComponent },
      { path:'dashboard', component: DashboardComponent , canActivate: [AuthGaurd]  }
    ])
  ],
  providers: [UserService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
