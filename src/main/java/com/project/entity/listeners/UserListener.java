package com.project.entity.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.project.entity.User;
import com.project.util.SequenceGeneratorService;

@Component
public class UserListener extends AbstractMongoEventListener<User> {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<User> event) {
	    if (event.getSource().getId() < 1) {
	        event.getSource().setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
	    }
	}
}
