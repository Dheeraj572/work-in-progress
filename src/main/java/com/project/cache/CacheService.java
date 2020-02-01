package com.project.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.project.entity.User;
import com.project.repository.IUserRepository;

@Component
public class CacheService {

	@Autowired
	private IUserRepository iUserRepository;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, List<User>> hashOperations;
	
	@PostConstruct
	public void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	public List<User> getCachedUserList(){
		List<User> cacheList = hashOperations.get("users", "users");
		if(cacheList == null) {
			cacheList = iUserRepository.findAll();
			hashOperations.put("users", "users", cacheList);
		}
		
		return cacheList;
		
	}
}
