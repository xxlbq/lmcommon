package com.lm.common.queue.delay;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.builder.ToStringBuilder;



public class DelayedObject<T> implements Delayed{

	private static final DateFormat df = DateFormat.getDateTimeInstance();
	
	private T t;
	private final long createTs;
	private final long expiredTs;
	public DelayedObject(T t, long expiredTs) {
		this(t, expiredTs, System.currentTimeMillis());
	}
	
	public DelayedObject(T t, long expiredTs, long createTsInMilli) {
		this.setT(t);
		this.expiredTs = expiredTs;
		this.createTs = createTsInMilli;
	}
	
	public long getDelay(TimeUnit unit) {
		return unit.convert((createTs+expiredTs)-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@SuppressWarnings("unchecked")
	public int compareTo(Delayed o) {
		DelayedObject<T> obj = (DelayedObject<T>)o;
		long thisVal = this.createTs;
		long anotherVal = obj.createTs;
		return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getT() == null) ? 0 : getT().hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelayedObject other = (DelayedObject) obj;
		if (getT() == null) {
			if (other.getT() != null)
				return false;
		} else if (!getT().equals(other.getT()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("targetObject", t)
		.append("createTime", df.format(new Date(createTs)))
		.append("expiredTs", expiredTs)
		.toString();
	}
	
	public void setT(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<DelayedObject> dq = new DelayQueue<DelayedObject>();
		System.out.println(System.currentTimeMillis());
		
		DelayedObject do1 = new DelayedObject("do1",( 1*1000),System.currentTimeMillis()+3000);
		DelayedObject do2 = new DelayedObject("do2",( 3*1000),System.currentTimeMillis()+1000);
		DelayedObject do3 = new DelayedObject("do3",( 2*1000),System.currentTimeMillis()+2000);
		DelayedObject do4 = new DelayedObject("do4",( 4*1000),System.currentTimeMillis()+4000);
		
		dq.put(do1);
		dq.put(do2);
		dq.put(do3);
		dq.put(do4);
		
		while (true){
			System.out.println("dq size="+dq.size());
			DelayedObject tk = dq.take();
			System.out.println(tk.getT());
		}
		
		
		
	}
}
