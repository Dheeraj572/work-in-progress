package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cache.CacheService;
import com.project.entity.User;
import com.project.exception.UserExistException;
import com.project.repository.IUserRepository;
import com.project.util.UserRequest;
import com.project.util.UserResponse;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CacheService cacheService;
	
	/*@Autowired
	private JavaMailSender javaMailSender;*/

	@Override
	public UserResponse registerUser(UserRequest userRequest) {

		User user = iUserRepository.findByUserNameOrEmailId(userRequest.getUserName(), userRequest.getEmailId());
		if (user != null) {
			throw new UserExistException("UserName/EmailId already exists");
		}

		user = objectMapper.convertValue(userRequest, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = iUserRepository.save(user);
		UserResponse userResponse = objectMapper.convertValue(user, UserResponse.class);
		
		//sendEmail(generateSimpleMailMessage(userRequest.getEmailId()));
		return userResponse;
	}

	@Override
	public Boolean validateUser(String userNameOrEmail, String password) {

		User user = iUserRepository.findByUserName(userNameOrEmail);
		if (user != null) {
			return passwordEncoder.matches(password, user.getPassword());
		}
		user = iUserRepository.findByEmailId(userNameOrEmail);
		if (user != null) {
			return passwordEncoder.matches(password, user.getPassword());
		}
		return false;
	}

	@Override
	public List<UserResponse> getAllUsers() {
		
		List<User> users = cacheService.getCachedUserList();
		List<UserResponse> userResponseList = users.stream().map(mapper -> objectMapper.convertValue(mapper, UserResponse.class)).collect(Collectors.toList());
		return userResponseList;
	}
/*
 * To be used for sending an email after registration
 * 
	private SimpleMailMessage generateSimpleMailMessage(String userEmail) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(userEmail);
		simpleMailMessage.setSubject("User registered");
		simpleMailMessage.setText("User registered");
		return simpleMailMessage;
		
	}
	
	private void sendEmail(SimpleMailMessage simpleMailMessage) {
		
		javaMailSender.send(simpleMailMessage);
	}
	
	*/

}
