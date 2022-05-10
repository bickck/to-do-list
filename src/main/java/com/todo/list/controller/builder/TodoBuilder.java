package com.todo.list.controller.builder;

import java.sql.Timestamp;

import com.todo.list.controller.dto.TodoDTO;
import com.todo.list.entity.UserEntity;

public class TodoBuilder {

	private Long id;

	private UserEntity user;

	private String title;

	private String content;

	private Timestamp createDate;


	public TodoBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public TodoBuilder setUser(UserEntity entity) {
		this.user = entity;
		return this;
	}

	public TodoBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public TodoBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public TodoBuilder setDate(Timestamp date) {
		this.createDate = date;
		return this;
	}
	
	public TodoDTO builder() {
		return new TodoDTO(id, user, title, content, createDate);
	}

}