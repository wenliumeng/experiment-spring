package com.everingthing.experimentboot.controller;

import com.everingthing.experimentboot.domain.User;
import com.everingthing.experimentboot.service.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private InfoRepository infoRepository;

	@Autowired
	public HelloWorldController(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
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

}
