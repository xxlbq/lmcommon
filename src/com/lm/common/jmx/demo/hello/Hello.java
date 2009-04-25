package com.lm.common.jmx.demo.hello;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class Hello implements HelloMBean ,Runnable,Hold {    
	
    private String name;    
   
    public String getName() {    
        return name;    
    }    
   
    public void setName(String name) {    
        this.name = name;    
    }    
   
    public void printHello() {    
        System.out.println("Hello World, " + name);    
    }    
   
    public void printHello(String whoName) {    
        System.out.println("Hello , " + whoName);    
    }


	public void begin() {
		run();
		
	}

	@Override
	public void run() {
		while(true){
			printHello();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}    
    
    
    
}   
	

