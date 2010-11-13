package com.lm.common.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DeadLock2 {
	
	public static void main(String args[]) throws Exception {
		//
		ExecutorService exec = Executors.newSingleThreadExecutor();
//		ExecutorService exec = Executors.newFixedThreadPool(10);
		
		//
		System.out.println("start to execute dead lock task...");
		Future<String> f1 = exec.submit(new DeadLockTask(exec));
		System.out.println("done, result: " + f1.get());
		
		//
		System.out.println("start to shutdown executor service...");
		exec.shutdown();
		exec.awaitTermination(70, TimeUnit.SECONDS);
		System.out.println("done");
	}
	
	private static class DeadLockTask implements Callable<String> {
		//
		private ExecutorService exec;
		
		public DeadLockTask(ExecutorService exec) {
			this.exec = exec;
		}
		
		public String call() throws Exception {
			Future<String> f = exec.submit(new SimpleTask());
			return f.get();
		}
	}
	
	private static class SimpleTask implements Callable<String> {
		
		public String call() throws Exception {
			return "simple task result";
		}
	}
}
