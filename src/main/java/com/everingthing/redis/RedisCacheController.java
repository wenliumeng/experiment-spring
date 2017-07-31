package com.everingthing.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RedisCacheController {

	@RequestMapping("/redis")
//	@Cacheable("test")
	public String getlist(String key){
		List<String> list = new ArrayList<String>();
		list.add(key + "1");
		list.add(key + "2");
		list.add(key + "3");
		list.add(key + "4");
		return "d";
	}

}
