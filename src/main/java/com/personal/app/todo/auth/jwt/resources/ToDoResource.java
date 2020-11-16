package com.personal.app.todo.auth.jwt.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.personal.app.todo.auth.jwt.model.ToDo;
import com.personal.app.todo.auth.jwt.service.ToDoService;

@RestController
@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin:*",origins = "http://localhost:4200")
public class ToDoResource {

	@Autowired
	private ToDoService todoService;

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos")
	public List<ToDo> getAllTodos(@PathVariable String userName) {
		return todoService.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable String userName, @PathVariable String id) {
		ToDo todo = todoService.deleteById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos/{id}")
	public ToDo getToDoItem(@PathVariable String userName, @PathVariable String id) {
		return todoService.findById(id);
	}

	@PostMapping("/users/{userName}/todos")
	public ResponseEntity<ToDo> createToDo(@PathVariable String userName, @RequestBody ToDo todo) {
		ToDo created = todoService.createToDo(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/users/{userName}/todos/{id}")
	public ResponseEntity<ToDo> updateToDo(@PathVariable String userName, @PathVariable String id,@RequestBody ToDo todo) {
		ToDo updatedTodo = todoService.updateToDoItem(todo);
		// ServletUriComponentsBuilder.fromCurrentContextPath().build();
		return new ResponseEntity<ToDo>(updatedTodo, HttpStatus.OK);
	}

}
