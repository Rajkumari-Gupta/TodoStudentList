package com.studentmanagement.todo.serviceimpl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.todo.entity.Todo;
import com.studentmanagement.todo.repository.TodoRepository;
import com.studentmanagement.todo.service.TodoService;
@Service
public class TodoServiceImpl implements TodoService{
	private TodoRepository todoRepo;
     @Autowired
	public TodoServiceImpl(TodoRepository todoRepo) {
		this.todoRepo = todoRepo;
	}

	@Override
	public Todo savetodo(Todo todo) {
		// TODO Auto-generated method stub
		return todoRepo.save(todo);
	}

	@Override
	public List<Todo> getAllTodo() {
		// TODO Auto-generated method stub
		List<Todo> allTodos=(List<Todo>)todoRepo.findAll();
		return allTodos;
	}

	@Override
	public Todo getTodoById(int id) {
		// TODO Auto-generated method stub
		Optional<Todo> optionalTodo = todoRepo.findById(id);
	    return optionalTodo.orElse(null);
	}

	@Override
	public Todo updateTodo(Todo todo) {
		// TODO Auto-generated method stub
		Optional<Todo> byId = todoRepo.findById(todo.getId());
        if(byId.isPresent()){
            return todoRepo.save(todo);
        }
        else
            return null;
    }
	

}
