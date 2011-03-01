package com.lm.common.jmx.inAction.demo1;

public interface HelloWorldMBean {
	public void setGreeting( String greeting );
	public String getGreeting();
	public void printGreeting();
}
