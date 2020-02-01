package com.project.constraintvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String>{

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")) {
			return true;
		}
		return false;
	}

}
