package com.lm.common.jmx.demo.xml;

import com.lm.common.jmx.demo.hello.Hold;




//public class Hello  implements HelloMBean ,Runnable,Hold{    
	public class Hello implements Hold{    
	
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

	@Override
	public void begin() {
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


//	public void begin() {
//		run();
//		
//	}

//	@Override
//	public void run() {
//		while(true){
//			printHello();
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}    
    
    
    
}   
	

