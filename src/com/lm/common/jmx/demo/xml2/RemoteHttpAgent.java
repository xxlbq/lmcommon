package com.lm.common.jmx.demo.xml2;

import java.io.InputStream;
import java.net.URL;

import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;

import com.lm.common.jmx.demo.xml.HelloAgent;
import com.sun.jdmk.comm.HtmlAdaptorServer;

public class RemoteHttpAgent {	
	private  MBeanServer mserver;	
	private  Registry  mregistry;	
	private  HtmlAdaptorServer madaptor=null;	
	public RemoteHttpAgent(int port){	
		mserver=createServer();		
		mregistry=createRegistry();		
		createHttpAdaptor(port);	
	}
	private   Registry createRegistry() {	
		Registry registry=null;			
		try {			
			
			InputStream stream = this.getClass().getResourceAsStream("mbean-descriptors.xml");

	
	
			Registry.loadRegistry(stream);			
			stream.close();			
			registry = Registry.getRegistry();		
			} 
		catch (Throwable t) {	
			t.printStackTrace(System.out);	
			System.exit(1);		
		}		
		return (registry);
	}	
	private MBeanServer createServer() {		
		if (mserver == null) {			
			try {				
				mserver = Registry.getServer();			 
			}
			catch (Throwable t) {	
				t.printStackTrace(System.out);		
				System.exit(1);			 
			}		  
		}		  
		return (mserver);	 
	}
	private void createHttpAdaptor(int port){	
		madaptor =	new HtmlAdaptorServer();	 
		//向Bean Server注册适配器对象	 
		try {	
			mserver.registerMBean(madaptor, 	
				new ObjectName("adaptor:protocol=HTTP"));	 
			} 
		catch (Exception e) {
			e.printStackTrace();	
		}	
		madaptor.setPort(port);	  
		madaptor.start();	
	}	
	public static void main(String[] args){	
		RemoteHttpAgent rha = new RemoteHttpAgent(80);		
		try {		
			rha.createMBean("ModelerRouter");		
		} 
		catch (Exception e) {	
			e.printStackTrace();
		}	
	}	
	public  void createMBean(String mname)	
		throws Exception {		
			ManagedBean managed = mregistry.findManagedBean(mname);		
			if (managed == null) {			
				Exception e = new Exception("ManagedBean is not found with "+mname);		
				throw new MBeanException(e);		
			}		
			String domain = managed.getDomain();		
			if (domain == null)			
				domain = mserver.getDefaultDomain();		
			Router r = new Router();		
			ModelMBean mbean = managed.createMBean(r);		
			ObjectName oname = new ObjectName(domain+":name=" + managed.getName());		
			mserver.registerMBean(mbean, oname);	
		}
	}

