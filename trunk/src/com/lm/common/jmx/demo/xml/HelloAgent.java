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
		//基于xml中的信息构建一个Registry    
		
		Registry registry = Registry.getRegistry(null, null);
		
		InputStream stream = HelloAgent.class.getResourceAsStream("mbeans-descriptors.xml");
		registry.loadMetadata(stream);
		stream.close();
		
		//由Registry得到一个MBeanServer    
		MBeanServer server = registry.getMBeanServer();

		//得到Hello在描述文件中的信息类，对应于xml文件<mbean>标签的name属性。    
		ManagedBean managed = registry.findManagedBean("Hello");
		//创建ObjectName    
		ObjectName helloName = new ObjectName("qqqqqqqqqqqqq"+ ":name=HelloWorld,port8083");
		
		 Hello h = new Hello();
		//得到ModelMBean    
		ModelMBean hello = managed.createMBean(h);
		
		//注册MBean    
		server.registerMBean(hello, helloName);
		
		
		

		ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
		
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		
		
		h.begin();
		
		
		System.out.println("start.....");
	}
}
