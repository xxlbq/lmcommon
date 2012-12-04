package com.lm.common.Observer2;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberWatcher extends Observable{

	private AtomicInteger n = new AtomicInteger(10);
	
	public void countDownNumber() throws InterruptedException{
		int m = n.intValue();
		for (int i = m; i >0; i--) {
			int v =n.getAndDecrement();
			this.setChanged();
			this.notifyObservers(v);
			Thread.sleep(1000);
		}
	}
	
}
