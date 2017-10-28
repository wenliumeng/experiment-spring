package com.everingthing.experimentboot.service;

import java.util.List;
import java.util.Map;

public interface MongoBase<T> {

	void insert(T object, String collectionName);

	T findOne(Map<String, Object> params, String collectionName);

	List<T> findAll(Map<String, Object> params, String collectionName);

	void update(Map<String, Object> params, String collectionName);

	void createCollection(String collectionName);

	void remove(Map<String, Object> params, String collectionName);
}
