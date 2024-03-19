package com.studentmanagement.todo.service;

import java.util.List;

import com.studentmanagement.todo.entity.Todo;

public interface TodoService {
   Todo savetodo(Todo todo);
	
   List<Todo> getAllTodo();

   Todo getTodoById(int id);


   Todo updateTodo(Todo todo);

}
