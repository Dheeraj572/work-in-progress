package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	
}
