package com.lm.common.util.xml;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

		
		private static NMock root;
		
		public static void initialize(String file) throws FileNotFoundException{
			root = javax.xml.bind.JAXB.unmarshal(new java.io.FileInputStream(file) , NMock.class);
		}
		
		private static final class NMock {
			public  List<RateCcpConfig> config = new ArrayList<RateCcpConfig>();
		}
		
		
		public static final List<RateCcpConfig> getConfig(){
			return root.config;
		}
}
