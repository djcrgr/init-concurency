package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterThreadSync implements Callable<Integer> {

    private Character literal;
    private final String message;
    private CountDownLatch latch;

    public CounterThreadSync(CountDownLatch latch, Character literal, String message) {
        this.latch = latch;
        this.literal = literal;
        this.message = message;
    }

    @Override
    public Integer call() {
        AtomicInteger res = new AtomicInteger();
        String text = message.toLowerCase();
        char[] charArray = text.toCharArray();
        for (char key : charArray) {
            if (key == literal) {
                res.addAndGet(1);
            }
        }
        latch.countDown();
        return res.get();
    }
}