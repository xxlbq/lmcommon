package com.lm.common.thread;


public class DeadLock3 {
	//
	private final int STACK_LENGTH = 1;
	
	//
	private int idx = 0;
	private String[] data = new String[STACK_LENGTH];

	public synchronized void push(String c) {
        while (idx == 1)  {
        	System.out.println("wait(push)" );
            try {wait();} catch (InterruptedException e) {}
		}
		data[idx] = c;
		idx++;
		System.out.println("after push,idx="+idx);
		notify();
	}
	
	public synchronized String pop() {
        while (idx == 0) {
        	System.out.println("wait(pop)"+ ",idx="+idx);
            try {wait();} catch(InterruptedException e) {}
		}
		idx--;
		System.out.println("after pop,idx="+idx);
		notify();
		return data[idx];
	}

    private class Producer extends Thread {
        public void run() {
            while (true) {
                String d = String.valueOf(Math.random());
                System.out.println("=====>>>push " + d);
                DeadLock3.this.push(d);
            }
        }
    }

    private class Consumer extends Thread {
        public void run() {
            while (true) {
                System.out.println("<<<====pop " + DeadLock3.this.pop());
            }
        }
    }

	/**
	 * 
wait(pop),idx=0
wait(pop),idx=0
=====>>>push 0.24974739862490003
after push,idx=1
after pop,idx=0
<<<====pop 0.24974739862490003
wait(pop),idx=0
wait(pop),idx=0
=====>>>push 0.3139803199324841
after push,idx=1
after pop,idx=0
<<<====pop 0.3139803199324841
wait(pop),idx=0
=====>>>push 0.35530276661128846
after push,idx=1
=====>>>push 0.35879356683190233
wait(push)
after pop,idx=0
after push,idx=1
=====>>>push 0.2400778303586435
wait(push)
after pop,idx=0
<<<====pop 0.35879356683190233
wait(pop),idx=0
<<<====pop 0.35530276661128846
wait(pop),idx=0
after push,idx=1
after pop,idx=0
wait(pop),idx=0
<<<====pop 0.2400778303586435
wait(pop),idx=0
=====>>>push 0.046354019491209875
after push,idx=1
=====>>>push 0.5525785381657217                 ======>这个push 的wait执行在pop的notifyall之后
wait(push)
after pop,idx=0                                 ======>这个pop的notifyall在put的wait之前
<<<====pop 0.046354019491209875
wait(pop),idx=0
wait(pop),idx=0
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        DeadLock3 c = new DeadLock3();
        Thread t1 = c.new Consumer();
        Thread t2 = c.new Consumer();
        Thread t3 = c.new Producer();
        t1.start();
		t2.start();
		t3.start();
	}
}


