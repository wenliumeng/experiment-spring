package com.everingthing.beginningspring.servlet;

import com.everingthing.beginningspring.Kc_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class HelloWorld extends HttpServlet {

	@Autowired
	private Kc_1 KcAppService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(KcAppService == null);
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		System.out.println(ac1.getBeanDefinitionCount());
		System.out.println(Arrays.asList(ac1.getBeanDefinitionNames()));
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		KcAppService = (Kc_1)ctx.getBean("KcAppService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<h1>" + KcAppService.getStr() + "</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req,resp);
	}

}
