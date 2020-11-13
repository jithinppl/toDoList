package com.todolist.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.todolist.model.User;
import com.todolist.repository.UserRepository;
import com.todolist.view.UserSignupView;
import com.todolist.view.UserView;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	public UserView addUser(UserSignupView userSignupView) {
		User user = new User() ;

		if(userRepository.validbyUserName(userSignupView.getUserName())!=1)
		{
			
		user.setPassword(hashPassword(userSignupView.getPassword()));
		user.setUserName(userSignupView.getUserName());

        User userSaved = userRepository.save(user);

        
        UserView userView = new UserView(userSaved);
       

        return userView;
        }
		
		return null;
	}
	
}
