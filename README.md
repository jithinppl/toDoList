# toDoList

## Introduction 

The task is to build an application in which a user can make a to do list. The main functionalities include login of user, adding task, removing task, updating task and displaying the task for a particular user. The backend uses Java and Spring Boot Framework for the creation of REST APIs whereas the front end uses Angular 2+ as the programming language. Also H2 database, which is an open source relational database management system is used for storing the data.

### Functionalities

1. User Sign Up: A new user can be created to perform his actions. Sign Up of a new user is created only in the back end and the front end page is not created for it. Using the REST Api  http://localhost:8080/todolist-2.4.0/api/user/signup, a new user can be created.
2. User Sign In: A user can sign into his account using his username and password.
3. Add Task: User can add a new task to his account.
4. Update Task: Using the update button, the existing task of a user can be updated. User can change his task description or can check the task as done.
5. Remove Task: User can login to his account and delete a particular task.
6. Display Task: This will display all the tasks in the list for the particular user.

## Architectural and Design Pattern
### Model view controller(MVC)
Model view controller is the architectural pattern  used to organize the code effectively. This helps to decouple code into components. We will be able to make the changes to the classes with minimal effects on the rest. The separation enables us to focus on a single part of implementation at a time.

### Command Pattern
The command pattern is a behavioural design pattern that is used to issue requests to objects without actually knowing anything about the action being requested.  Command allows the requester of a particular action to be decoupled from the object that performs the action. In other words, it encapsulates a request as an object. This isolation provides flexibility in the timing and order of commands. In this assignment I have created a Command interface which contains an execute function. This interface is implemented for the main three functionalities Add task, Update Task and Remove Task. Based on the API request for the action, the particular command will be executed and the action related to the functionality performs. 

## Security
Have used Bcrypt and JWT for securing the application.
1. Bcrypt: bcrypt is a password hashing function which is used to improve security. In this assignment, bcrypt is used to encode the user password while creating a new user. Passwords will be encoded to a hash value and will thus be saved to the database. This helps in improving the security of the login credentials.
2. JWT: JWT is used for authenticating and securing data transfer between parties. A JSON Web token is generated at the time of login. Later, this token will be used as a bearer header for all API services. This guarantees the system security which is a quality attribute.

## Unit Testing
JUnit and Mockito are used for the unit testing of the different functionalities. JUnit is done for User SignIn whereas Mockito, which is an open source framework, is used for testing the Add and Remove Task functionality.

## Future Improvements
1. While creating a user, an option can be added for creating a user group. In such a way, all the users in the group can do the different functionalities to the particular list.
2. Also, I would have added the front end for the user SIgnUp page, if there's more time.


