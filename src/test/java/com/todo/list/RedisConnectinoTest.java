package com.todo.list;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.todo.list.test.Entity.RedisEntity;
import com.todo.list.test.Entity.RedisTestEntity;
import com.todo.list.test.Entity.TestEntity;
import com.todo.list.test.repository.EventRepository;
import com.todo.list.test.repository.RedisTestRepository;
import com.todo.list.test.repository.TestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConnectinoTest {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private RedisTestRepository redisTestRepository;

	@Test
	public void testRedisRepositoryInsertJob() {
		long eventId = 1;
		eventRepository.save(new RedisEntity(eventId));
	}

	// @Test
	public void testCacheableInsertJob() {
	
	}

	@Test
	public void testRedisRepositorySelectJob() {
		eventRepository.findAll().forEach((data) -> {
			System.out.println("hi");
			System.out.println(data);
		});

	}

	@Test
	public void testRedisRepositorydeleteJob() {
		long eventId = 1;
		eventRepository.delete(new RedisEntity(eventId));
	}

	@Test
	public void testTestInsertRedisTestEntity() {
//		long id = 2;
//		redisTestRepository.save(new RedisTestEntity(new TestEntity(id)));
	}

	@Test
	public void testTestCachefindAll() {
		Iterable<RedisTestEntity> itr = redisTestRepository.findAll();

		System.out.println(itr);
	}
}
