package com.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public long generateSequence(String seqName) {

		Query query = new Query(Criteria.where("_id").is(seqName));

		Update update = new Update();
		update.inc("sequence", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true).upsert(true);

		DatabaseSequence seqId = mongoOperations.findAndModify(query, update, options, DatabaseSequence.class);

		return seqId.getSequence();

	}
}
