package com.everingthing.beginningspring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service(value = "KcAppService")
public class Kc_1Impl implements Kc_1, ApplicationListener<ContextRefreshedEvent> {

	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (event.getApplicationContext().getParent() == null) {
			System.out.println("\n\n\n\n\n______________\n\n\n加载了\n\n_________\n\n");
		}

		//或者下面这种方式
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			System.out.println("\n\n\n_________\n\n加载一次的 \n\n ________\n\n\n\n");
		}
	}

	public String getStr() {
		return "str";
	}
}
