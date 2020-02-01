package com.project.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	private long id;
	private String userName;
	private String emailId;
	private String password;

}
