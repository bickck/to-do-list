package com.todo.list.controller.builder;

import java.sql.Timestamp;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.entity.UserEntity;

public class QuoteBuilder {
	private Long id;
	private UserEntity userEntity;
	private String quote;
	private String author;

	public QuoteBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public QuoteBuilder setUser(UserEntity entity) {
		this.userEntity = entity;
		return this;
	}

	public QuoteBuilder setQuote(String quote) {
		this.quote = quote;
		return this;
	}

	public QuoteBuilder setAuthor(String author) {
		this.author = author;
		return this;
	}

	public QuoteDTO builder() {
		return new QuoteDTO(id, quote, author);
	}
}
