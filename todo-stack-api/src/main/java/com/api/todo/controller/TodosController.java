package com.api.todo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.todo.model.Todo;
import com.api.todo.service.TodoService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodosController {
	@Autowired
	private TodoService service;
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return service.findAll();
	}
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> removeTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = service.deleteById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo findById(@PathVariable String username, @PathVariable long id) {
		Todo todo = service.findById(id);
		return todo;
	}	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = service.saveTodo(todo);
		// Location - Get current resource url - {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> modifyTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
		Todo todoUpdate = service.saveTodo(todo);
		return new ResponseEntity<Todo>(todoUpdate, HttpStatus.OK);
	}
	
}
