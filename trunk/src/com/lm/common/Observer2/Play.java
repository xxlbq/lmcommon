package com.lm.common.Observer2;

public class Play {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		NumberWatcher nw = new NumberWatcher();
		Observer1 o1 = new Observer1();
		Observer2 o2 = new Observer2();
		
		nw.addObserver(o1);
		nw.addObserver(o2);
		
		nw.countDownNumber();
	}
}
