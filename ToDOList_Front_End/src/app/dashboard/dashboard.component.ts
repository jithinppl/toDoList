import { TaskService } from './../task.service';
import { Task } from './../Task';
import { UserService } from './../user.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  taskList;
  isadding: boolean = false;
  newdescription: string = "";
  constructor(private userService: UserService, private taskService: TaskService) {
    this.getAllTaskList();
  }


  ngOnInit(): void {
  }
  getAllTaskList() {
    this.taskList=this.taskService.getTasklist();

  }
  Logout() {
    this.userService.Logoutuser();
  }
  onedit(task: Task) {
    task.iseditable = true;
    return;
  }
  onupdate(task: Task) {
    console.log(task);
    this.taskService.updatetask(task).subscribe();
    task.iseditable = false;
    return;
  }
  checkupdated(task: Task) {
    task.check = !task.check;
    this.onupdate(task);
    return;
  }
  oncancel(task: Task) {
    task.iseditable = false;
    return;
  }
  onremove(task: Task) {
    this.taskService.removetask(task).subscribe(res => {
      this.getAllTaskList();
      task.iseditable=false;
    });
  }
  addnewtask() {
    this.isadding = true;
    return;
  }
  oncreate() {
    this.taskService.createtask(this.newdescription).subscribe(res => {
      this.getAllTaskList();
      this.isadding=false;
      this.newdescription="";
    },error => {console.log(error)});
  }
  oncreatecancel() {
    this.isadding = false;
    this.newdescription="";
  }
}
