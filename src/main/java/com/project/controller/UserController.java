package com.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@CrossOrigin
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
	
	@ResponseStatus(code=HttpStatus.OK)
	@ApiOperation(value="Validate user credentials")
	@ApiResponses(value= {@ApiResponse(code=200,message="User Validated",response=Boolean.class)})
	@GetMapping("validateUser")
	public Boolean validateUser(@RequestParam(required=true) String userNameOrEmail, @RequestParam(required=true) String password) {
		
		return iUserService.validateUser(userNameOrEmail, password);
	}
	
}
