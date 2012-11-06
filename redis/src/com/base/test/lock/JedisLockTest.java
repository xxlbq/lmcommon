package com.base.test.lock;



import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class JedisLockTest {
	
	private final String APP_NAME="JedisLockTest";
	Jedis jedis = new Jedis("10.18.20.26", 6379);
	
	public static void main(String[] args) {
		JedisLockTest t = new JedisLockTest();		
		System.out.println(t.testLock());		
	}
	
	public boolean testLock() {
		String pk = "mypk";
		String lockPk = "lock" + pk;  
		String lockValue = String.valueOf(System.currentTimeMillis()) + APP_NAME;  
		 
		try {  
		    if(jedis.exists(lockPk)) { // other one locked already   
		        System.out.println("Already locked pk:" + pk);  
		        return false;  
		    }  
		  
		    jedis.watch(lockPk);// make sure below operation is particle   
		      
		      
		    Transaction trans = jedis.multi();
		    trans.set(lockPk, lockValue);  
		    trans.expire(lockPk, 60*60);  
		    List<Object> ret = trans.exec();  
		      
		    if(ret == null) {  
		    	System.out.println("Concurrent lock fail for pk:" + pk);  
		        return false;  
		    }  
		      
		    String lockedValue = jedis.get(lockPk);  
		      
		    return lockValue.equals(lockedValue); // locked success   
		 } catch (Exception e) {  
			System.out.println("Exception in lock for pk:" + pk);   
		    return false;  
		}
	}
}
