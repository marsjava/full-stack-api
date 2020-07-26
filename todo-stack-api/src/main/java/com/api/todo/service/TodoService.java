package com.api.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.todo.model.Todo;
@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static long idCount = 0;
	static {
		todos.add(new Todo(++idCount, "marikannan", "Learn Full Stack.", new Date(), false));
		todos.add(new Todo(++idCount, "marikannan", "Learn DevOps", new Date(), false));
		todos.add(new Todo(++idCount, "shanmuga", "Learn Tailoring with All Blouses.", new Date(), false));
		todos.add(new Todo(++idCount, "shanmuga", "Learn Tailoring with Sudidhar.", new Date(), false));
		todos.add(new Todo(++idCount, "karkuvel", "Learn 5th Std Lessons.", new Date(), false));
		todos.add(new Todo(++idCount, "saravanavel", "Learn 3rd Std Lessons.", new Date(), false));
		todos.add(new Todo(++idCount, "srihari", "Learn English and Tamil Rhymes", new Date(), false));
	}
	public List<Todo> findAll() {
		return todos;
	}
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null) return null;
		if(todos.remove(todo)) return todo;
		return null;
	}
	public Todo findById(long id) {
		for(Todo todo: todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	public Todo saveTodo(Todo todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCount);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
}
