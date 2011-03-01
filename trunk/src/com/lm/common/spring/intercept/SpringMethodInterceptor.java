package com.lm.common.spring.intercept;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SpringMethodInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invo) throws Throwable {
	   Object[] object = invo.getArguments();
	   try{
	    String date1 = (new Date()).toLocaleString();
	    System.out.println("信息：[MethodInterceptor]["+date1+"]用户 "+object[0]+" 正在尝试登录陆系统...");
	   
	   Object returnObject = invo.proceed();
	   
	    String date2 = (new Date()).toLocaleString();
	    System.out.println("信息：[MethodInterceptor]["+date2+"]用户 "+object[0]+" 成功登录系统.");
	   
	    return returnObject;
	   }
	   catch(Throwable throwable){
	    if(object[0].equals("Jessery")){
	     throw new Exception("信息：[MethodInterceptor]不允许黑名单中用户 "+object[0]+" 登录系统");
	    }
	   }
	   return object;
	}

}