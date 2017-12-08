package com.everingthing.experimentboot.controller;

import com.everingthing.experimentboot.dao.JobDao;
import com.everingthing.experimentboot.domain.Job;
import com.everingthing.experimentboot.service.DemoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MongoController {

	private JobDao jobDao;

	public JobDao getJobDao() {
		return jobDao;
	}

	@Autowired
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@RequestMapping("/mongogetjob1")
	@Cacheable(value = "one-key")
	public void getJob(){
		Map<String,Object> params=new HashMap<String,Object>(1);
		params.put("cid", "5959c76db0dfce192dfd1495");
		Job job = (Job)jobDao.findOne(params,"Java");
		System.out.println(job.toString());
	}

	//--------------

	@Autowired
	DemoInfoService demoInfoService;


	@RequestMapping("/test")
	public@ResponseBody
	String test(){
		Job loaded = demoInfoService.findById("5959c76db0dfce192dfd149b");
		System.out.println("loaded="+loaded);
		Job cached = demoInfoService.findById("5959c76db0dfce192dfd149b");
		System.out.println("cached="+cached);
		loaded = demoInfoService.findById("5959c76db0dfce192dfd14a3");
		System.out.println("loaded2="+loaded);
		return"ok";
	}


	@RequestMapping("/delete")
	public@ResponseBody String delete(String id){
		demoInfoService.deleteFromCache(id);
		return"ok";
	}

	@RequestMapping("/test1")
	public@ResponseBody String test1(){
		demoInfoService.test();
		System.out.println("DemoInfoController.test1()");
		return"ok";
	}
}
