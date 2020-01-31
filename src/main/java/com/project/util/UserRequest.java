package com.project.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Pattern(regexp = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}",message="Invalid emailId")
	private String emailId;

	@NotNull(message = "Password cannot be null")
	@ApiModelProperty(position = 3)
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9])\\S{8,}$",message="Invalid password")
	private String password;

}
