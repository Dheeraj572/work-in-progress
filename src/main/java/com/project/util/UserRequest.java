package com.project.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.constraintvalidators.ValidEmail;
import com.project.constraintvalidators.ValidPassword;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

	@ApiModelProperty(position = 1)
	@NotNull(message = "UserName cannot be null")
	@Pattern(regexp = "^(?!\\s*$).+", message = "UserName cannot be empty")
	private String userName;

	@NotNull(message = "EmailId cannot be null")
	@ApiModelProperty(position = 2)
	@ValidEmail
	private String emailId;

	@NotNull(message = "Password cannot be null")
	@ApiModelProperty(position = 3)
	@ValidPassword
	private String password;

}
