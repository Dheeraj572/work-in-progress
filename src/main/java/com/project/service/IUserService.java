package com.project.service;

import com.project.util.UserRequest;
import com.project.util.UserResponse;

public interface IUserService {

	UserResponse registerUser(UserRequest userRequest);
	Boolean validateUser(String userName, String password);
}
