package com.lm.common.thread.docHomeWork;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * 
 * 
 * 
 * 
 * 4.3 习题三
假设以下方法需要执行长时间的计算，为了提高性能, 所以计算后缓存了计算结果。
为了能在多线程环境下安全的使用该方法，应如何对其进行修改？要求：
	当两个线程以不同的参数同时调用该方法的时候，能并发计算
	当两个线程以相同的参数同时调用该方法的时候，不能重复计算

private HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();

public Integer calculate(Integer param) throws Exception{
    Integer result = results.get(param);
    if(result != null) {
        return result;
    }
    result = doCalculate(param);
    results.put(param, result);
    return result;
}

 * 
 * @author xxlbq
 *
 */
public class Practice3 {
	
//	private HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
//	private HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
	
//	private Map<Integer, Integer> m = new HashMap<Integer,Integer>();
	
//	ConcurrentMap<Integer,AtomicBoolean>  temp= new ConcurrentHashMap<Integer,AtomicBoolean>();
	private HashMap<Integer,AtomicBoolean> temp = new HashMap<Integer,AtomicBoolean>();
	
	ConcurrentMap<Integer,Future<Integer>> results = new ConcurrentHashMap<Integer,Future<Integer>>();
//	ConcurrentMap<Integer,Integer> results = new ConcurrentHashMap<Integer,Integer>();
//	AtomicInteger
	public static AtomicBoolean ai = new AtomicBoolean(false);
//	public ReentrantLock lock = new ReentrantLock();

	CyclicBarrier cb = new CyclicBarrier(2);
	
	private HashMap<Integer, AtomicBoolean> flags = new HashMap<Integer, AtomicBoolean>();
//	volatile
	
	
//	public Integer calculate(Integer param) throws Exception{
//		
//	    if(results.get(param) == null) {
//	    	 
//	    	Integer temp = results.
//	    	if(temp == null){
//	    		Integer ing= doCalculate(param);
//	    		results.putIfAbsent(param,ing);
//	    		return ing ;
//	    	}else{
//	    		results.put(param, temp);
//	    		return temp;
//	    	}
//
//		    
//	    }
//
//	    return results.get(param);
		
	
//		
//		if(temp.containsKey(param)){
//			return results.get(param);
//		}else{
//			temp.put(param,new AtomicBoolean(true));
//			
//			if(temp.get(param).getAndSet(false)){
//				Integer result = doCalculate(param);
//				results.put(param, result);
//
//			}
//
//			return results.get(param);
//		}
//	    
//	}


	
	public Integer calculate(final Integer param) throws Exception{ 
//	    while(true) { 
	        Future<Integer> f = results.get(param); 
	        if(f == null) { 
	            Callable<Integer> eval = new Callable<Integer>() { 
	                public Integer call() throws Exception { 
	                    return doCalculate(param); 
	                } 
	            }; 
	            FutureTask<Integer> ft = new FutureTask<Integer>(eval); 
	            f = results.putIfAbsent(param, ft); 
	            if(f == null) { 
	                f = ft; 
	                ft.run(); 
	            } 
	        } 
	         
	        try { 
	            return f.get(); 
	        } catch(CancellationException e) { 
	            results.remove(param, f); 
	        } catch(ExecutionException e) { 
	            try {
					throw e.getCause();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	        }
			return null; 
	    } 
//	} 
	
	
	private Integer doCalculate(Integer param) {
		
		System.out.println(" I am calculate "+param);
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return param.intValue()+ 10000000;
	}
	
	public static void main(String[] args) {
		
		final Practice3 p3 = new Practice3();
		new Thread(new Runnable() {
					
			@Override
			public void run() {
				try {
					for (int i = 0; i < 100; i++) {
						Integer ing = p3.calculate(i);

						System.out.println("T1:"+ing);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for (int i = 0; i < 100; i++) {
						Integer ing = p3.calculate(i);
						System.out.println("T2:"+ing);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}).start();
		
		
		}


}
