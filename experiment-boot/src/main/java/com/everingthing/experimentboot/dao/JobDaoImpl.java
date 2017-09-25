package com.everingthing.experimentboot.dao;

import com.everingthing.experimentboot.domain.Job;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository("jobDao")
public class JobDaoImpl implements JobDao {

	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public void insert(Object object, String collectionName) {
		mongoTemplate.insert(object, collectionName);
	}

	@Override
	public void createCollection(String collectionName) {
		mongoTemplate.createCollection(collectionName);
	}

	@Override
	public void remove(Map params, String collectionName) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))), Job.class, collectionName);
	}

	@Override
	public void update(Map params, String collectionName) {
		mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))), new Update().set("name", params.get("name")), Job.class,collectionName);
	}

	@Override
	public List findAll(Map params, String collectionName) {
		List<Job> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), Job.class,collectionName);
		return result;
	}

	@Override
	public Object findOne(Map params, String collectionName) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))), Job.class,collectionName);

	}
}
