package com.lm.common.util.obj;


public class ObjectCommonUtilImp implements CommonUtil{
	
//	private transient  static Logger logger = 
//		Logger.getLogger(ObjectCommonUtil.class);
	
	int code = -1 ;
	
    public  boolean isEmpty(Object obj){
    	return obj == null ? true :false;
    }

    public  boolean isNotEmpty(Object obj) {
        return !(isEmpty(obj));
    }
    
    public  boolean isNotEmpty(int param) {
    	return !(isEmpty(param));
    }
    
    
    public ObjectCommonUtilImp(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
    
    
  
}
