package com.personal.app.todo.auth.jwt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.personal.app.todo.auth.jwt.model.ToDo;

/**
 * It will be having services of todo data
 * 
 * @author rbathini
 *
 */
@Component
public class ToDoService {

	private static List<ToDo> todos = new ArrayList<>();
	private static int counter = 0;

	static {
		todos.add(new ToDo(++counter, "Ramesh bathini", "Software engineer", true, new Date()));
		todos.add(new ToDo(++counter, "Chiranjeevi", "Film Actor", true, new Date()));
		todos.add(new ToDo(++counter, "sachin Tendulkar", "Cricketer", true, new Date()));
		todos.add(new ToDo(++counter, "James gosling", "Developer", true, new Date()));
		todos.add(new ToDo(++counter, "Abdul kalam", "scientist", true, new Date()));
	}

	public List<ToDo> findAll() {
		return todos;
	}

	public ToDo deleteById(String id) {
		ToDo todo = findById(id);
		if (todos.contains(todo)) {
			todos.remove(todo);
		}
		return todo;
	}

	public ToDo findById(String id) {
		for (ToDo todo : todos) {
			if (todo.getId() == Long.valueOf(id)) {
				return todo;
			}
		}
		return null;
	}

	public ToDo createToDo(ToDo todo) {
		todo.setId(++counter);
		todo.setDesc(todo.getDesc());
		todo.setTargetDate(todo.getTargetDate());
		todo.setDone(todo.isDone());
		todos.add(todo);
		return todo;
	}

	public ToDo updateToDoItem(ToDo todoItem) {
		for (ToDo todo : todos) {
			if (todo.getId() == Long.valueOf(todoItem.getId())) {
				todo.setDesc(todoItem.getDesc());
				todo.setTargetDate(todoItem.getTargetDate());
				todo.setDone(todoItem.isDone());
				return todo;
			}
		}
		return null;
	}
}
