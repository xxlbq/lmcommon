package com.lm.common.DynProxy;

import java.lang.reflect.Proxy;

public class Play {
	
	
	public static void main(String[] args) {
		
		MoMoWu s = new MoMoWu("MOMOWU");
		SingerDecorator sd = new SingerDecorator(s,"MoMoWu");
		Singer pxy = (Singer)Proxy.newProxyInstance(
				s.getClass().getClassLoader(), 
				s.getClass().getInterfaces(), 
				sd);
		
		pxy.sing();
		
		LiangBo lb = new LiangBo("LiangBo");
		SingerDecorator lbsd = new SingerDecorator(lb,"LiangBo");
		Singer pxylb = (Singer)Proxy.newProxyInstance(
				lb.getClass().getClassLoader(), 
				lb.getClass().getInterfaces(), 
				lbsd);
		
		pxylb.sing();
		
		
	}
	
	
	
}
