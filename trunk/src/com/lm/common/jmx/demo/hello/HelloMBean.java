package com.lm.common.jmx.demo.hello;

/* HelloMBean.java - MBean interface describing the management   
 operations and attributes for the Hello World MBean.  In this case   
 there are two operations, "sayHello" and "add", and two attributes,   
 "Name" and "CacheSize". */

public interface HelloMBean {    
	
    public String getName();    
   
    public void setName(String name);    
   
    public void printHello();    
   
    public void printHello(String whoName);    

    
} 
