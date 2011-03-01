package com.lm.common.spring.intercept;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LoginAfterReturningAdvice implements AfterReturningAdvice{



	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		
		System.out.println(" 用户 "+arg2[0]+" 成功登录系统.");
//		Object result = arg0.invoke(arg2, arg1);
		
		
	}

}
