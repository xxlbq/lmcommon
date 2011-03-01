package com.lm.common.spring.intercept;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LoginMethodBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		
		System.out.println("用户"+arg1[0]+" 正在尝试登录陆系统...");
//		Object result = arg0.invoke(arg2, arg1);
		
	}

}
