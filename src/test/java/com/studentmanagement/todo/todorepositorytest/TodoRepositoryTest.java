package com.studentmanagement.todo.todorepositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.studentmanagement.todo.entity.Todo;
import com.studentmanagement.todo.repository.TodoRepository;

@ExtendWith(MockitoExtension.class)
public class TodoRepositoryTest {
	@Mock
	private TodoRepository todoRepo;
	
	@BeforeEach
	public void setUp() {
		int id = 1;
        Todo todo = new Todo(id, "raj", "udemy", "java", "Pending");
        when(todoRepo.findById(id)).thenReturn(Optional.of(todo));
	}
	@AfterEach
	public void tearDown() {
		reset(todoRepo);
	}
	@Test
    public void testFindById_ValidId() {
        
        int id = 1;
        Optional<Todo> result = todoRepo.findById(id);
        assertEquals("raj", result.get().getName());
        assertEquals("udemy", result.get().getTitle());
        assertEquals("java", result.get().getDescription());
        assertEquals("Pending", result.get().getStatus());
        
        verify(todoRepo, times(1)).findById(id);
	}
        @Test
        public void testFindById_InvalidId() {
            
            int id = 2; 
            Optional<Todo> result = todoRepo.findById(id);

            
            assertEquals(Optional.empty(), result);
            
            verify(todoRepo, times(1)).findById(id);
        }
        
        @Test
        public void testSaveTodo() {
            
            Todo todoToSave = new Todo();
            todoToSave.setName("Rajk");
            todoToSave.setTitle("aws");
            todoToSave.setDescription("cloud");
            todoToSave.setStatus("Pending");
            
            
            when(todoRepo.save(todoToSave)).thenReturn(todoToSave);
            
           
            Todo savedTodo = todoRepo.save(todoToSave);
            
            
            assertEquals("Rajk", savedTodo.getName());
            assertEquals("aws", savedTodo.getTitle());
            assertEquals("cloud", savedTodo.getDescription());
            assertEquals("Pending", savedTodo.getStatus());
            
            verify(todoRepo, times(1)).save(todoToSave);
        }

        
        
    }

