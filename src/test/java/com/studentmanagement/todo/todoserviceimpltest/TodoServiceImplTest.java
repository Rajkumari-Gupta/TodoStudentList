package com.studentmanagement.todo.todoserviceimpltest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.studentmanagement.todo.entity.Todo;
import com.studentmanagement.todo.repository.TodoRepository;
import com.studentmanagement.todo.serviceimpl.TodoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {
	@InjectMocks
	private TodoServiceImpl todoServiceImpl;
	@Mock
	private TodoRepository todoRepo;
	
	private Todo todo,todo1,todo2;
	private List<Todo> todoList;
	private Optional optional;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		todo=new Todo(1,"rajkumari","udemycourse","java 8 programming language","pending");
		todo1=new Todo(2,"anita","udemycourse","complete java 17 programming language","NotStarted");
		todo2=new Todo(3,"Anjay","AwsCertification","cloud practioner","complete");
		
		todoList = new ArrayList<>();
		todoList.add(todo);
		todoList.add(todo1);
		todoList.add(todo2);
		optional=Optional.of(todo);
	}
	@AfterEach
	public void teardown() {
		todo= null;

}
	@Test
    public void givenTodoToSaveThenShouldReturnSavedTodo() {
        when(todoRepo.save(todo)).thenReturn(todo);
        Todo responseTodo = todoServiceImpl.savetodo(todo);
        verify(todoRepo,times(1)).save(todo);
        assertEquals(todo,responseTodo);
    }


	@Test
	public void getAllUsersList() {
		when(todoRepo.findAll()).thenReturn(todoList);
		List<Todo> result=todoServiceImpl.getAllTodo();
		assertEquals(todoList.size(), result.size());
		}
	@Test
	public void givenIdThenReturnTodo() {
		when(todoRepo.findById(1)).thenReturn(optional);
		Todo response=todoServiceImpl.getTodoById(1);
		assertEquals(todo,response);
	}
	@Test
	public void givenByIdthenreturnUpdated() {
		when(todoRepo.findById(1)).thenReturn(optional);
		when(todoRepo.save(any(Todo.class))).thenReturn(todo);
		Todo response=todoServiceImpl.updateTodo(todo);
		assertEquals(todo,response);
	}
	
}
