package com.everingthing.experimentboot;

import com.everingthing.experimentboot.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class RedisTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		User user = new User();
		user.setUserName("zhangsan");
		user.setPassWord("aaa");
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		operations.set("com.neo", user);
		//超时时间检测
//		operations.set("com.neoby", user, 10, TimeUnit.SECONDS);

		Thread.sleep(1000);
//		redisTemplate.delete("com.neo");

		boolean exists = redisTemplate.hasKey("com.neoby");
		if (exists) {
			System.out.println("exist is true");
		} else {
			System.out.println("exist is false");
		}
	}


}
