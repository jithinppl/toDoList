package com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todolist.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	

	   @Query(value="Select count(1) from user u where u.username=:userName",nativeQuery=true)
	   int validbyUserName(@Param("userName") String userName);
	   
	   @Query(value="Select * from user u where u.username=:userName LIMIT 1",nativeQuery=true)
	   User GetuserbyName(@Param("userName") String userName);

}
