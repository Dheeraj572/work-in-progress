package com.project.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.exception.ErrorResponse;
import com.project.service.IUserService;
import com.project.util.UserRequest;
import com.project.util.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@ApiOperation(value="Register a user")
	@ApiResponses(value= {@ApiResponse(code=201,message="User Registered",response=UserResponse.class),
			@ApiResponse(code=400,message="Validation Errors",response=ErrorResponse.class),
			@ApiResponse(code=409,message="User Details already exist",response=ErrorResponse.class)})
	@PostMapping("register")
	public ResponseEntity<?> registerUser(@RequestBody @ApiParam(value="Request object to register user",required=true) @Valid UserRequest  userRequest){
		
		UserResponse userResponse = iUserService.registerUser(userRequest);
		return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
	}
}
