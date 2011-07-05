package com.lm.common.jmx.demo.ibm.standard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer; 
import javax.management.MBeanServerFactory; 
import javax.management.ObjectName; 
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import sun.management.ManagementFactory;
public class Main { 
   private static ObjectName objectName ; 
   private static MBeanServer mBeanServer; 
   private static JMXConnectorServer connectorServer;
   
   private static String registryPort = "8765";
   private static String registryPath = "JmxTest01";
   
   public static void main(String[] args) throws Exception{ 
       init(); 
       manage();      
       start();
   } 
   private static void start() throws IOException {
	   LocateRegistry.getRegistry(registryPort);
		JMXServiceURL address = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + registryPort + "/" + registryPath);
		Map<String, String> env = new HashMap<String, String>();  
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");  
//		env.put(Context.PROVIDER_URL, "rmi://localhost:" + registryPort); 
		connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(address, env, mBeanServer);
		
		//
		System.out.println("bf jmx connector server started, url: " + address.toString());
		connectorServer.start();
		System.out.println("jmx connector server started, url: " + address.toString());
	
	}
   
   private static void init() throws Exception{ 
       ServerImpl serverImpl = new ServerImpl(); 
       ServerMonitor serverMonitor = new ServerMonitor(serverImpl); 
//       mBeanServer = MBeanServerFactory.createMBeanServer(); 
       mBeanServer = ManagementFactory.createPlatformMBeanServer();
//       objectName = new ObjectName("MyJmxTest:name="+registryPath+",port="+registryPort); 
//       objectName = new ObjectName("MyJmxTest:name="+registryPath); 
       objectName = new ObjectName("HelloAgent:name=ServerMonitor1"); 
//       mBeanServer.registerMBean(serverMonitor,objectName);  
       mBeanServer.registerMBean(serverMonitor,objectName);  
       
   } 
   
   private static void manage() throws Exception{ 
	   new Thread(
		    new Runnable() {
			public void run() {
				   while(true){
					   try {
						   Long upTime = (Long) mBeanServer.getAttribute(objectName,"UpTime"); 
						   System.out.println(upTime); 
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
		    }
			}
	   ).start();

   } 
} 
