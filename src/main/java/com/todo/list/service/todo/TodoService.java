package com.todo.list.service.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.UserTodoEntity;
import com.todo.list.repository.TodoRepository;

@Service
public class TodoService {

	private TodoRepository repository;

	@Autowired
	public TodoService(TodoRepository todoRepository) {
		this.repository = todoRepository;
	}

	@Transactional
	public void todoSave(TodoDTO todoDTO) {
		repository.save(new UserTodoEntity(null, null, null));
	}

	@Transactional
	public void todoUpdate(TodoDTO todoDTO) {
		repository.save(null);
	}

	@Transactional
	public void todoDelete(Long id) {
		repository.deleteById(id);
	}
}