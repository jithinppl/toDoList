package com.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todolist.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value="Select * from task t where t.taskid=:taskId",nativeQuery=true)
	   Task getTaskById(@Param("taskId") int taskId);
	
	@Query(value="Select * from task t where t.userid=:userId",nativeQuery=true)
	   List<Task> findTaskbyUserId(@Param("userId") int userId);

}
