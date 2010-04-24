package com.lm.common.util.obj;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.lm.common.util.date.DateCommonUtil;

public class ObjectCreator {

	@SuppressWarnings("unchecked")
	public <T> Class<T> getClass(String id  , Class<T> clz) throws Exception {
		return (Class<T>) Class.forName(id);
	}
	
	public <T> T getObject(String id ,Class<T> clz , Object... args) throws Exception {
		
		List<Class<?>> clzLst = null;
		if(args !=null){
			clzLst = new ArrayList<Class<?>>(args.length);
			for(Object c : args){
				clzLst.add(c.getClass());
			}
		}
		
		Constructor<T> c = getClass(id,clz).getDeclaredConstructor(
				clzLst == null ? null :
				clzLst.toArray(new Class<?>[clzLst.size()]));
	
		if(!c.isAccessible()){
			c.setAccessible(true);
		}
		
		return (T)(c.newInstance(args));
	}
	
	
	public static void main(String[] args) throws Exception {
		
		Object nothing = null;
		Object have = new Object();
		
		ObjectCreator oc = new ObjectCreator();
		
		
		
		CommonUtil cu = oc.getObject("com.lm.common.util.obj.ObjectCommonUtilImp",
				ObjectCommonUtilImp.class, 200);
		
		System.out.println(cu.isEmpty(nothing)); 
		System.out.println(cu.isEmpty(have)); 
		System.out.println(cu.getCode()); 
	}
	
}
