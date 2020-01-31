package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.entity.User;

public interface IUserRepository extends MongoRepository<User, Long>{

	User findByUserNameOrEmailId(String userName, String emailId);
}
