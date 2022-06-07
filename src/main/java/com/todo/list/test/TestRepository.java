package com.todo.list.test;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

	// boolean existsByTest(String test);
	Page<TestEntity> findAll(Pageable pageable);
}
