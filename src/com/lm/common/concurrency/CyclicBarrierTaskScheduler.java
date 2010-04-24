package com.lm.common.concurrency;



import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTaskScheduler implements Runnable {

	private CyclicBarrier cyclicBarrier;
	private int batchTaskNumbers;
	private int realtimeTaskNumbers;

	// you can set an ExecutorService extenally
	private ExecutorService executor = Executors.newFixedThreadPool(10);

	public void run() {
		// pre-validate on states of current object

//		cyclicBarrier = new CyclicBarrier(getBatchTaskNumbers(),
				cyclicBarrier = new CyclicBarrier(5,
				new Runnable() {
					public void run() {
						System.out.println("B");
						// for (int i = 0; i < getRealtimeTaskNumbers(); i++) {
						// getExecutor().execute(new RealtimeTask());
						// }
					}
				});

		for (int i = 0; i < getBatchTaskNumbers(); i++) {
			getExecutor().execute(new Runnable() {
				public void run() {
					((Runnable) new BatchTask()).run();
					try {
						getCyclicBarrier().await();
					} catch (InterruptedException e) {
						e.printStackTrace(); // process exception as per your
						// need
					} catch (BrokenBarrierException e) {
						e.printStackTrace(); // process exception as per your
						// need
					}
				}
			});

		}

	}

	public void shutdown() {
		if (getExecutor() != null) {
			getExecutor().shutdown();
			try {
				getExecutor().awaitTermination(Integer.MAX_VALUE,
						TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace(); // process exception as per your need
			}
		}
	}

	public CyclicBarrier getCyclicBarrier() {
		return cyclicBarrier;
	}

	public int getBatchTaskNumbers() {
		return batchTaskNumbers;
	}

	public void setBatchTaskNumbers(int batchTaskNumbers) {
		this.batchTaskNumbers = batchTaskNumbers;
	}

	public int getRealtimeTaskNumbers() {
		return realtimeTaskNumbers;
	}

	public void setRealtimeTaskNumbers(int realtimeTaskNumbers) {
		this.realtimeTaskNumbers = realtimeTaskNumbers;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public static void main(String[] args) {
		CyclicBarrierTaskScheduler taskScheduler = new CyclicBarrierTaskScheduler();
		taskScheduler.setBatchTaskNumbers(10);
		taskScheduler.setRealtimeTaskNumbers(15);

		try {
			taskScheduler.run();
		} finally {
			// taskScheduler.shutdown();
		}
	}
}