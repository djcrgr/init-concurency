package com.getjavajob.training.KarpovN.concurency.lesson10;

import com.getjavajob.training.KarpovN.concurency.StopWatch;
import com.getjavajob.training.KarpovN.concurency.lesson07.QuickSortComparable;
import com.getjavajob.training.KarpovN.concurency.lesson07.QuickSortComparableThread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class QuickSortsByTime {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Integer[] array = new Integer[30_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        QuickSortComparable<Integer> quickSortComparable = new QuickSortComparable<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        quickSortComparable.quicksort(array);
        System.out.println(stopWatch.getElapsedTime() + " in ms for one Thread");
        QuickSortComparableThread<Integer> quickSortComparableThread = new QuickSortComparableThread<>(array, 0, array.length - 1);
        stopWatch.start();
        quickSortComparableThread.quicksort(array);
        System.out.println(stopWatch.getElapsedTime() + " in ms for Thread-based");
        QuickSortForkJoin<Integer> quickSortForkJoin = new QuickSortForkJoin<>(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        stopWatch.start();
        forkJoinPool.invoke(quickSortForkJoin);
        forkJoinPool.shutdown();
        System.out.println(stopWatch.getElapsedTime() + " in ms for Fork/Join");
    }
}
//   https://gyazo.com/46fe68e7f609f18e2e0a1582e99c308e