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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
//	ConcurrentMap<Integer,Result> results = new ConcurrentHashMap<Integer,Result>();
//	AtomicInteger
	public static AtomicBoolean ai = new AtomicBoolean(false);
//	public ReentrantLock lock = new ReentrantLock();

	
	
	private HashMap<Integer, AtomicBoolean> flags = new HashMap<Integer, AtomicBoolean>();
//	volatile
	
	
	
//	public Integer calculate(Integer param) throws Exception{
//	    Integer result = results.get(param);
//	    if(result != null) {
//	        return result;
//	    }
//	    result = doCalculate(param);
//	    results.put(param, result);
//	    return result;
//	}
	
	class Result{
		private Integer res ;
		private Thread cal ;
		
		public Thread getCal() {
			return cal;
		}
		public void setCal(Thread cal) {
			this.cal = cal;
		}
		
		public Result() {}
		public Result(Integer value) {
			res = value;
		}
		public Integer getResult() {
			return res;
		}
		public void setResult(Integer result){
			this.res = result;
		}
//		public Integer getResult(Integer v) {
//			res = doCalculate(v);
//			return res ;
//		}
	}
	
	
	public Integer calculate(final Integer param) throws Exception{
		
		if(results.get(param) == null){
			Callable<Integer> cal = new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					return doCalculate(param);
				}
			};
			
			FutureTask<Integer> ft = new FutureTask<Integer>(cal);
			
				Future<Integer> f= results.putIfAbsent(param, ft);
				if(f == null){
					f = ft;
					ft.run();
				}
			return f.get();
		}
		
		return results.get(param).get();
	}
	
	
//	public Integer calculate(final Integer param) throws Exception{
//		
//		
//		final Result r = new Result();
//		
//	    if(results.get(param) == null) {
//	    	
//	    	Thread dot = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					
//					r.setResult(doCalculate(param));
//					
////					Result orin = results.putIfAbsent(param,r);
////					
////					if(orin == null){
////						
////						r.getResult(param);
////					}else{
////						r.setResult(orin.getResult()) ; 
////					}
//						
//				}
//			});
//	    	
//	    	Result orgin = results.putIfAbsent(param, r);
//	    	
//	    	if(null == orgin || null == orgin.getResult()){
//		    	r.setCal(dot);
//		    	dot.start();
//		    	dot.join();
//	    	}
//
//
//	    	return r.getResult();
//	    }
//
//	    if(r.getCal().isAlive()){
//	    	r.getCal().join();
//	    }
//	    
//	    return results.get(param).getResult();
//	    
//	}


	
	
	
	
	
	
	
	
	
//	public Integer calculate(final Integer param) throws Exception{ 
//	    while(true) { 
//	        Future<Integer> f = results.get(param); 
//	        if(f == null) { 
//	            Callable<Integer> eval = new Callable<Integer>() { 
//	                public Integer call() throws Exception { 
//	                    return doCalculate(param); 
//	                } 
//	            }; 
//	            FutureTask<Integer> ft = new FutureTask<Integer>(eval); 
//	            f = results.putIfAbsent(param, ft); 
//	            if(f == null) { 
//	                f = ft; 
//	                ft.run(); 
//	            } 
//	        } 
//	         
//	        try { 
//	            return f.get();
//	            
//	        } catch(CancellationException e) { 
//	            results.remove(param, f); 
//	        } catch(ExecutionException e) { 
//	            try {
//					throw e.getCause();
//				} catch (Throwable e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} 
//	        }
//			return null; 
//	    } 
//	} 
//	
	
	private Integer doCalculate(Integer param) {
		
		System.out.println(" I am calculate "+param);
		try {
			Thread.sleep(100);
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
