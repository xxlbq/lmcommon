package com.lm.common.jmx.demo.csdn.practice2;

public interface QueueSamplerMXBean { 

    public QueueSample getQueueSample(); 

    public void clearQueue(); 
    
    public void add();

} 
