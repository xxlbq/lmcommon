package com.lm.common.spring.intercept;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		   String name = "shirdrn";
		   String pwd = "830119";
		   
		   
		   ApplicationContext ctx = new ClassPathXmlApplicationContext("com/lm/common/spring/intercept/applicationContext.xml");
		   AccountService asi = (AccountService)ctx.getBean("accountService");
//		   AccountService asi = (AccountService)((ProxyFactoryBean)ctx.getBean("accountService")).getTargetSource();
		   asi.login(name, pwd);
		}
}
