package com.lm.common.util.prop;

import junit.framework.TestCase;

public class TestLogic extends TestCase {

	public void test1(){
		boolean a = true;
		boolean b = false;
		
		
		
		assertTrue(a | b);
	}
	
	public void test2(){
		boolean a = true;
		boolean b = false;
		
		
		
		assertTrue(a | a);
	}
	
	public void test3(){
		boolean a = true;
		boolean b = false;
		
		
		
		assertTrue(b | b);
	}
	
	public void test4(){
		
		boolean a = true;
		boolean b = false;
		
		boolean c  = false; 
		boolean d  = true;
		
//		a|=true;
		a|=false;
		assertTrue(a);
		
		
//		b|=true;
//		b|=false;
//		assertTrue(b);
	}
	
}
