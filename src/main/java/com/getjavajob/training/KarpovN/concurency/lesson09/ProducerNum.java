package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ProducerNum implements Runnable{

    private ProgramNum programNum;
    private CyclicBarrier barrier;
    private Semaphore semaphore;

    public ProducerNum(Semaphore semaphore, ProgramNum programNum) {
        this.semaphore = semaphore;
        this.programNum = programNum;
    }



    public ProducerNum(CyclicBarrier barrier, ProgramNum programNum) {
        this.barrier = barrier;
        this.programNum = programNum;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            programNum.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}
