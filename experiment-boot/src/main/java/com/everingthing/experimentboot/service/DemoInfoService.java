package com.everingthing.experimentboot.service;

import com.everingthing.experimentboot.domain.Job;

public interface DemoInfoService {

	Job findById(String id);

	void deleteFromCache(String id);

	void test();
}
