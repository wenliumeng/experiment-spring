package com.everingthing.experimentboot.service;

import com.everingthing.experimentboot.domain.Job;
import org.springframework.data.repository.CrudRepository;

public interface DemoInfoRepository extends CrudRepository<Job,String>{
}
