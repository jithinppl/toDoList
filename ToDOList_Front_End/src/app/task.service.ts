import { UserService } from './user.service';
import { map } from 'rxjs/operators';
import { Task } from './Task';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  httpOptions;
  constructor(private http: HttpClient, private userService:UserService) {


  }

  getTasklist() {
    let url = "http://localhost:8080/todolist-2.4.0/displaytask";
    return this.http.get<Task[]>(url);
  }
  updatetask(task: Task) {
    let updatetask = {
      taskId: task.taskId,
      description: task.description,
      isCheck: task.check
    }
    let url = "http://localhost:8080/todolist-2.4.0/updatetask";
    return this.http.post(url, updatetask, { responseType: 'text'});
  }
  createtask(taskdescription:string) {
    let createtask = {
      description: taskdescription
    }
    let url = "http://localhost:8080/todolist-2.4.0/addtask";
    return this.http.post(url, createtask, {  responseType: 'text' });
  }
  removetask(task: Task) {
    let removetask = {
      taskId: task.taskId
    }
    let url = "http://localhost:8080/todolist-2.4.0/removetask";
    return this.http.post(url, removetask, { responseType: 'text'});
  }
}
