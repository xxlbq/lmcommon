package com.lm.common.util.xml;

import java.util.List;

public class RunTest {
	
	public static void main(String[] args) {
		try {
			RunTest.start(RunTest.class.getResource("nmock.xml").getPath());
			
			System.out.println("config file path:" + RunTest.class.getResource("nmock.xml").getPath());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void start(String file) throws Exception{
		
		ConfigLoader.initialize(file);
		
		List<RateCcpConfig> lst = ConfigLoader.getConfig();
		
		initRateGenerator(lst);

	}
	
	public static void initRateGenerator(List<RateCcpConfig> lst) throws Exception{
		
		for(RateCcpConfig c : lst){
			
			System.out.println("RateCcpConfig:" + c);
		}
	}
}
