package com.everingthing.experimentboot.controller;

import com.everingthing.experimentboot.domain.Job;
import com.everingthing.experimentboot.domain.User;
import com.everingthing.experimentboot.service.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private InfoRepository infoRepository;

	private RedisTemplate redisTemplate;

	@Autowired
	public HelloWorldController(InfoRepository infoRepository, RedisTemplate redisTemplate)
	{
		this.infoRepository = infoRepository;
		this.redisTemplate = redisTemplate;
	}

	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}

	@RequestMapping("/getUser")
	public User getUser() {
		User user = new User();
		user.setUserName("张三");
		user.setPassWord("xxx");
		return user;
	}

	@RequestMapping("/javacount")
	public String findAccount(){
		return String.format("%d", infoRepository.findAll().size());
	}

	@RequestMapping("/find")
	public String find() {
		return infoRepository.findOne("5959c76db0dfce192dfd1495").toString();
	}

	@RequestMapping("/redis1")
	@Cacheable(value = "job-key")
	public Job getJobToRedis() {
		return infoRepository.findOne("5959c76db0dfce192dfd1495");
	}

	@RequestMapping("/redis2")
	public Job getJobToRedis2() {
		if (redisTemplate.hasKey("job-key")){
			System.out.println("has redis");
			return (Job) redisTemplate.opsForValue().get("job-key");
		}else {
			System.out.println("not has redis");
			return infoRepository.findOne("5959c76db0dfce192dfd1495");
		}
	}
}
