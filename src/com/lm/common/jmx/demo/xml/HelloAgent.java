package com.lm.common.jmx.demo.xml;

import java.io.InputStream;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	public static void main(String[] args) throws Exception {
		//åŸºäºŽxmlä¸­çš„ä¿¡æ�¯æž„å»ºä¸€ä¸ªRegistry    
		
		Registry registry = Registry.getRegistry(null, null);
		
		InputStream stream = HelloAgent.class.getResourceAsStream("mbeans-descriptors.xml");
		registry.loadMetadata(stream);
		stream.close();
		
		//ç”±Registryå¾—åˆ°ä¸€ä¸ªMBeanServer    
		MBeanServer server = registry.getMBeanServer();

		//å¾—åˆ°Helloåœ¨æ��è¿°æ–‡ä»¶ä¸­çš„ä¿¡æ�¯ç±»ï¼Œå¯¹åº”äºŽxmlæ–‡ä»¶<mbean>æ ‡ç­¾çš„nameå±žæ€§ã€‚    
		ManagedBean managed = registry.findManagedBean("Hello");
		//åˆ›å»ºObjectName    
		ObjectName helloName = new ObjectName("qqqqqqqqqqqqq"+ ":name=HelloWorld,port=8083");
		
		 Hello h = new Hello();
		//å¾—åˆ°ModelMBean    
		ModelMBean hello = managed.createMBean(h);
		
		//æ³¨å†ŒMBean    
		server.registerMBean(hello, helloName);
		
		
		

		ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
		
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		
		
		h.begin();
		
		
		System.out.println("start.....");
	}
}
