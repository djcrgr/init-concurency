package com.getjavajob.training.KarpovN.concurency;

public class StopWatch {

    public long startTime;
    public long endTime;

    public long start() {
        startTime = System.currentTimeMillis();
        return startTime;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}