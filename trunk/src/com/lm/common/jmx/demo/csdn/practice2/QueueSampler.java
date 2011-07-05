package com.lm.common.jmx.demo.csdn.practice2;

import java.util.Date;

import java.util.Queue;

public class QueueSampler implements QueueSamplerMXBean {

	private Queue queue;

	public QueueSampler(Queue queue) {

		this.queue = queue;

	}

	public QueueSample getQueueSample() {

		synchronized (queue) {

			return new QueueSample(new Date(), queue.size(), (String)queue.peek());
//			return (QueueSample)queue;

		}

	}

	public void clearQueue() {

		synchronized (queue) {

			queue.clear();

		}

	}

	@Override
	public void add() {
		

		synchronized (queue) {

			queue.add("add "+System.currentTimeMillis());

		}
	}

}
