package com.studentmanagement.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.todo.entity.Todo;
import com.studentmanagement.todo.service.TodoService;

@RestController
@RequestMapping("/cts")
public class TodoController {
	private TodoService todoService;
    @Autowired
	public TodoController(TodoService todoService) {
	
		this.todoService = todoService;
	}
    @PostMapping("/todo")
    public ResponseEntity<Todo> savetodo(@RequestBody Todo todo){
    	return new ResponseEntity<>(todoService.savetodo(todo),HttpStatus.CREATED);
    }
	@GetMapping("/alltodos")
	public ResponseEntity<List<Todo>> getAllTodo() {
        return new ResponseEntity<>(todoService.getAllTodo(),HttpStatus.OK);
    }
	@GetMapping("/todo/{todoid}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("todoid") int todoid) {
        return new ResponseEntity<>(todoService.getTodoById(todoid),HttpStatus.OK);
    }
	@PutMapping("/todo")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo),HttpStatus.OK);
    }

}
