package com.everingthing.experimentboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class thymeleafController {

	@RequestMapping(value = "/sb1",method = RequestMethod.GET)
	public String index(ModelMap map) {
		map.addAttribute("host", "http://www.baidu.com");
		return "index";
	}
}
