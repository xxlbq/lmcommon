package com.lm.common.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThread {

	Lock lock = new ReentrantLock();
	boolean flag = true;
	
	
	
	class AThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try{
//					lock.lock();
					
					flag = true;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(" AThread flag:"+flag);
				}finally {
//					lock.unlock();
				}

			}
		}
		
	}
	class BThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try{
//					lock.lock();
					
					flag = false;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("BThread flag:"+flag);
				}finally {
//					lock.unlock();
				}

			}

			
		}
		
	}
	
	public static void main(String[] args) {
		TwoThread tt = new TwoThread();
		Thread a = new Thread(tt.new AThread());
		Thread b = new Thread(tt.new BThread());
		a.start();
		b.start();
		
	}
}
