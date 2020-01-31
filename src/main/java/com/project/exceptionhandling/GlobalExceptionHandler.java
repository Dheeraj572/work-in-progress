package com.project.exceptionhandling;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.project.exception.ErrorResponse;
import com.project.exception.UserExistException;
import com.project.exception.UserValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = UserValidationException.class)
	public ErrorResponse handleUserValidationException(UserValidationException ex) {
		List<String> errorList = new ArrayList<>();
		errorList.add(ex.getMessage());
		return new ErrorResponse(errorList);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errorList = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(action -> errorList.add(action.getDefaultMessage()));
		return new ErrorResponse(errorList);
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(UserExistException.class)
	public ErrorResponse handleUserExistException(UserExistException ex) {
		List<String> errorList = new ArrayList<>();
		errorList.add(ex.getMessage());
		return new ErrorResponse(errorList);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { JsonMappingException.class, JsonParseException.class })
	public ErrorResponse handleJsonMappingException() {
		List<String> errorList = new ArrayList<>();
		errorList.add("Unable to parse data");
		return new ErrorResponse(errorList);
	}
}
