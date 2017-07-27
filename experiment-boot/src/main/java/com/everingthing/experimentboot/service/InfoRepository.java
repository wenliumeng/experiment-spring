package com.everingthing.experimentboot.service;

import com.everingthing.experimentboot.domain.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfoRepository extends MongoRepository<Job, String> {
}
