package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class EvenOddConcurrent {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        ProgramNum programNum = new ProgramNum();
        ProducerNum producerNum = new ProducerNum(cyclicBarrier,programNum);
        ConsumerNum consumerNumOdd = new ConsumerNum(cyclicBarrier,programNum, true);
        ConsumerNum consumerNumEven = new ConsumerNum(cyclicBarrier,programNum, false);
        Thread producerThread = new Thread(producerNum);
        Thread thread = new Thread(consumerNumOdd);
        Thread thread1 = new Thread(consumerNumEven);
        producerThread.start();
        thread.start();
        thread1.start();*/
        Semaphore semaphore = new Semaphore(3);
        ProgramNum programNum = new ProgramNum();
        ProducerNum producerNum = new ProducerNum(semaphore,programNum);
        ConsumerNum consumerNumOdd = new ConsumerNum(semaphore, programNum, true);
        ConsumerNum consumerNumEven = new ConsumerNum(semaphore,programNum, false);
        Thread producerThread = new Thread(producerNum);
        Thread thread = new Thread(consumerNumOdd);
        Thread thread1 = new Thread(consumerNumEven);
        producerThread.start();
        thread.start();
        thread1.start();
    }
}


