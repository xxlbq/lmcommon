package com.lm.common.jmx.demo.hello;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.lm.common.jmx.demo.dynamic.HelloDynamic;
import com.lm.common.jmx.demo.jack.HelloListener;
import com.lm.common.jmx.demo.jack.Jack;



public class HelloAgent {    
	
    public static void main(String[] args) throws Exception {
    	
//      MBeanServer server = MBeanServerFactory.createMBeanServer();    
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();   
        
        ObjectName helloName = new ObjectName("chengang:name=HelloWorld,port=8083");
        
//        HelloMBean hb = new Hello();
        HelloDynamic hb = new HelloDynamic();
        
        server.registerMBean(hb, helloName);
        

        
        
        Jack jack = new Jack();    //重点    
        server.registerMBean(jack, new ObjectName("HelloAgent:name=jack,port=8084"));    //重点    
        jack.addNotificationListener(new HelloListener(), null, hb);    //重点    
        
        
        
        
        hb.begin();
        
        
        
        
        //域名:name=MBean名称
//        ObjectName adapterName = new ObjectName(    
//                "HelloAgent:name=htmladapter,port=8082");
//        
//        
//        HtmlAdaptorServer adapter = new HtmlAdaptorServer();    
//        
//        server.registerMBean(adapter, adapterName);    
//        
//        adapter.start();    
        
        System.out.println("start.....");    
    }  
    
 
}  
