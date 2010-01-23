package com.lm.common.queue.delay;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DelayedTasker implements Runnable {
	
     DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();

    public void addTask(DelayedTask e) {
       queue.put(e);
     }

    public void removeTask() {
       queue.poll();
     }

    public Collection<DelayedTask> getAllTasks() {
       return Collections.unmodifiableCollection(queue);
     }

    public int getTaskQuantity() {
       return queue.size();
     }

    public void run() {
       while (!queue.isEmpty())
           try {
               queue.take().run();
           } catch (InterruptedException e) {
               System.out.println("Interrupted");
           }
       System.out.println("Finished DelayedTask");
     }
    
    public static class DelayedTask implements Delayed, Runnable {
        private static int counter = 0;
        private final int id = counter++;
        private final int delta;
        private final long trigger;

        public DelayedTask(int delayInSeconds) {
            delta = delayInSeconds;
            trigger = System.nanoTime() + NANOSECONDS.convert(delta, SECONDS);
        }

        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
        }

        public int compareTo(Delayed arg) {
            DelayedTask that = (DelayedTask) arg;
            if (trigger < that.trigger)
               return -1;
            if (trigger > that.trigger)
               return 1;
            return 0;
        }

        public void run() {
            //run all that you want to do
            System.out.println(this);
        }

        public String toString() {
            return "[" + delta + "s]" + "Task" + id;
        }
      }

    public static void main(String[] args) {
        Random rand = new Random();
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayedTasker tasker = new DelayedTasker();
        for (int i = 0; i < 10; i++){
        	int delaySec = rand.nextInt(5);
            tasker.addTask(new DelayedTask(delaySec));
            System.out.println("delaySec:"+ delaySec);
        }
        exec.execute(tasker);
        exec.shutdown();
      }
    }