package com.everingthing.experimentboot.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Controller
@EnableWebMvc
public class WebController extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("1：" + "addViewControllers");
		registry.addViewController("/index.html").setViewName("index");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		System.out.println("2:" + "configureDefaultServletHandling");
		configurer.enable();
	}

	@Bean
	public ViewResolver getViewResolver() {
		System.out.println("3：" + "getViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("templates/");
		//resolver.setSuffix(".html");
		return resolver;
	}

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String getHomePage(){
		return "index";
	}

}
