package com.lm.common.DynProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SingerDecorator implements InvocationHandler {

	private Object p;
	private String mName;
	
	public SingerDecorator(Object obj,String methodName) {
		this.p = obj;
		this.mName = methodName;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
//		Field teacher = p.getClass().getField("teacher");
//		teacher.setAccessible(true);
////		String teacherName = (String)teacher.get(p);  value ----->庾澄庆
//		
		Object r = null;
//		
//		if(teacherName.contains(mName)){
			
			Method t = p.getClass().getMethod(method.getName()+mName, null);
			t.setAccessible(true);
			System.out.println(" 唱歌准备 ...");
			r = t.invoke(p, args);
			System.out.println(" 唱歌结束 ...");

//		}else{
//			
//			System.out.println(" 唱歌准备 ...");
//			r = method.invoke(p, args);
//			System.out.println(" 唱歌结束 ...");
//		}
		
		return r;
		
	}

}
