package com.studentmanagement.todo.todocontrollertest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentmanagement.todo.controller.TodoController;
import com.studentmanagement.todo.entity.Todo;
import com.studentmanagement.todo.service.TodoService;

@SpringBootTest
	@AutoConfigureMockMvc
	public class TodoControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @Mock
	    private TodoService todoService;

	    @InjectMocks
	    private TodoController todoController;

	    private List<Todo> todoList;

	    @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
	        todoList = new ArrayList<>();
	        Todo todo1 = new Todo(1, "John", "Task 1", "Description 1", "Pending");
	        Todo todo2 = new Todo(2, "Alice", "Task 2", "Description 2", "Completed");
	        todoList.add(todo1);
	        todoList.add(todo2);
	    }

	    @Test
	    public void testGetAllTodo() throws Exception {
	        when(todoService.getAllTodo()).thenReturn(todoList);

	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cts/alltodos"))
	                .andReturn();

	        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	        assertNotNull(result.getResponse().getContentAsString());
	    }

	    @Test
	    public void testGetTodoById() throws Exception {
	        int todoId = 1;
	        Todo todo = new Todo(todoId, "John", "Task 1", "Description 1", "Pending");
	        when(todoService.getTodoById(todoId)).thenReturn(todo);

	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cts/todo/{todoid}", todoId))
	                .andReturn();

	        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	        assertNotNull(result.getResponse().getContentAsString());
	    }

	    @Test
	    public void testSaveTodo() throws Exception {
	        Todo todo = new Todo();
	        todo.setName("John");
	        todo.setTitle("Task 1");
	        todo.setDescription("Description 1");
	        todo.setStatus("Pending");

	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonTodo = objectMapper.writeValueAsString(todo);

	        when(todoService.savetodo(todo)).thenReturn(todo);

	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cts/todo")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonTodo))
	                .andReturn();

	        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
	        assertNotNull(result.getResponse().getContentAsString());
	    }

	    @Test
	    public void testUpdateTodo() throws Exception {
	        Todo todo = new Todo(1, "John", "Task 1", "Description 1", "Completed");

	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonTodo = objectMapper.writeValueAsString(todo);

	        when(todoService.updateTodo(todo)).thenReturn(todo);

	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/cts/todo")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonTodo))
	                .andReturn();

	        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	        assertNotNull(result.getResponse().getContentAsString());
	    }
	}
