package com.todo.list.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.configs.token.AuthenticationJwtToken;
import com.todo.list.repository.UserRepository;

@RestController
public class TestController {

	@Autowired
	private TestService service;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationJwtToken authenticationJwtToken;

	@GetMapping("/test/ArgCache")
	public String testArgCache() {
		long id = 1;
		String testEntity = service.testArgCacheService(id);
		return testEntity;
	}

	@GetMapping("/test/noArgCache")
	public String testNoArgCache() {

		String testEntity = service.testNoArgCacheService();
		return testEntity;
	}
	
	@GetMapping("/test/jwtProblem")
	public String testJwtProblem() {
		
		String token = authenticationJwtToken.makeToken(repository.findByUsername("username0"));
		
		return token;
	}
}
