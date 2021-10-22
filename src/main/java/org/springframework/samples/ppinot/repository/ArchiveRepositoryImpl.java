package org.springframework.samples.ppinot.repository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.samples.ppinot.domain.Log;

public class ArchiveRepositoryImpl implements ArchiveRepositoryCustom{

	private final MongoTemplate mongoTemplate;

	  @Autowired
	  public ArchiveRepositoryImpl(MongoTemplate mongoTemplate) {
	    this.mongoTemplate = mongoTemplate;
	  }
	
	@Override
	public Set<Log> query(DynamicQuery dynamicQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
