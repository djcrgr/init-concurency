package com.getjavajob.training.KarpovN.concurency.lesson09;

import java.util.LinkedList;
import java.util.Random;

public class ProgramNum {


    private LinkedList<Integer> list = new LinkedList<>();
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void produce() throws InterruptedException {
        while (true) {
            while (list.size() > 0) {
                synchronized (this) {
                    wait();
                }
            }
            Random random = new Random();
            int value = random.nextInt();
            setFlag(value % 2 == 0);
            list.add(value);
            System.out.println("Producer produced- " + value);
            synchronized (this) {
                notify();
            }
            Thread.sleep(500);
        }
    }

    public void consume(boolean flag) throws InterruptedException {
        while (true) {
            while (list.size() == 0) {
                synchronized (this) {
                    wait();
                }
            }
            if (isFlag() == flag) {
                list.removeLast();
                System.out.println(isFlag() ? "odd " + Thread.currentThread().getName() : "even " + Thread.currentThread().getName());
            }
            synchronized (this) {
                notify();
            }
        }
    }
}
