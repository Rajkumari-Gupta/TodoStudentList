package com.studentmanagement.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.todo.entity.Todo;
@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer>{
	List<Todo> findByName(String name);
}
