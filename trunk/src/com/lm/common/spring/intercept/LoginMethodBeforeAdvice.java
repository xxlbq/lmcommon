package com.lm.common.spring.intercept;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LoginMethodBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		
		System.out.println("�û�"+arg1[0]+" ���ڳ��Ե�¼½ϵͳ...");
//		Object result = arg0.invoke(arg2, arg1);
		
	}

}
