package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ConsumerNum implements Runnable{


    private CyclicBarrier cyclicBarrier;
    private Semaphore semaphore;

    public ConsumerNum(Semaphore semaphore, ProgramNum programNum, boolean flag) {
        this.semaphore = semaphore;
        this.programNum = programNum;
        this.flag = flag;
    }

    private ProgramNum programNum;
    private boolean flag;

    public ConsumerNum(CyclicBarrier cyclicBarrier, ProgramNum programNum, boolean flag) {
        this.cyclicBarrier = cyclicBarrier;
        this.programNum = programNum;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            programNum.consume(flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}
