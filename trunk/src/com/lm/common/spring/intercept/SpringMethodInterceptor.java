package com.lm.common.spring.intercept;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SpringMethodInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invo) throws Throwable {
	   Object[] object = invo.getArguments();
	   try{
	    String date1 = (new Date()).toLocaleString();
	    System.out.println("��Ϣ��[MethodInterceptor]["+date1+"]�û� "+object[0]+" ���ڳ��Ե�¼½ϵͳ...");
	   
	   Object returnObject = invo.proceed();
	   
	    String date2 = (new Date()).toLocaleString();
	    System.out.println("��Ϣ��[MethodInterceptor]["+date2+"]�û� "+object[0]+" �ɹ���¼ϵͳ.");
	   
	    return returnObject;
	   }
	   catch(Throwable throwable){
	    if(object[0].equals("Jessery")){
	     throw new Exception("��Ϣ��[MethodInterceptor]��������������û� "+object[0]+" ��¼ϵͳ");
	    }
	   }
	   return object;
	}

}