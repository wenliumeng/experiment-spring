package com.everingthing.experimentboot.controller;

import com.everingthing.experimentboot.domain.Job;
import com.everingthing.experimentboot.domain.User;
import com.everingthing.experimentboot.service.InfoRepository;
import com.everingthing.experimentboot.utils.SpringUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
public class HelloWorldController {

	private InfoRepository infoRepository;

	private RedisTemplate<String, Object> redisTemplate;

	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	public HelloWorldController(InfoRepository infoRepository)
	{
		this.infoRepository = infoRepository;
//		this.redisTemplate = redisTemplate;
	}

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	@Autowired
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
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

	@RequestMapping("/testredis")
	public void testRedis() {
		//-------------测试redis连接性-------start------
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		Job job = new Job();
		job.setCid("123");
		job.setCompany("cs");
		operations.set("one","Job{cid='123', company='cs'}");
		System.out.println(stringRedisTemplate.opsForValue().get("one"));
		//-------------测试redis连接性-------end------
	}

	/**
	 * 切换缓存
	 * @return
	 */
	@RequestMapping("/getjob")
	public Job getJobToMongodbOrRedis() {
		Job job = infoRepository.findOne("5959c76db0dfce192dfd1495");
		if (redisTemplate.hasKey("one-key")){
			System.out.println("redis");
			return (Job) redisTemplate.opsForValue().get("one-key");
		}else {
			System.out.println("mongodb");
			redisTemplate.opsForValue().set("one-key",job);
			return infoRepository.findOne("5959c76db0dfce192dfd1495");
		}
	}

	/**
	 * 使用注解
	 * @return
	 */
	@RequestMapping("/getjob1")
	@Cacheable(value = "user-key")
	public Job getJobToMongodbOrRedis1() {
		Job job = infoRepository.findOne("5959c76db0dfce192dfd1495");
		System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return job;
	}

	@RequestMapping("/show")
	public void show() {
		for (String str : Arrays.asList(SpringUtil.getApplicationContext().getBeanDefinitionNames())){
			System.out.println(str);
		}
	}

	@RequestMapping("/t1")
	@Cacheable(value = "redis")
	public String testCache(String username) {
		String s = username+Math.random();
		System.out.println(s);
		return s;
	}
}
