package com.lm.common.util.str;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class StringCommonUtil {
	
//	private transient  static Logger logger = 
//		Logger.getLogger(StringCommonUtil.class);
	
	
	
    public static boolean isEmpty(String param) {
        return ( null == param || param.length() < 1);
    }
    
    
    public static boolean isNotEmpty(String param) {
        return !(isEmpty(param));
    }
    
	public static String reflectionToString(Object obj){
		return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE);
	}
    
    
}
