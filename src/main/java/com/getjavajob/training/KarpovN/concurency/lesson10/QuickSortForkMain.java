package com.getjavajob.training.KarpovN.concurency.lesson10;

import java.util.concurrent.ForkJoinPool;

public class QuickSortForkMain {
    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * i);
        }
        for (Integer integer : arr) {
            System.out.println(integer + " first");
        }
        QuickSortForkJoin<Integer> quickSortForkJoin = new QuickSortForkJoin<>(arr);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(quickSortForkJoin);
        pool.shutdown();
        for (Integer integer : arr) {
            System.out.println(integer);
        }

    }
}
