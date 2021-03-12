package com.getjavajob.training.KarpovN.concurency.lesson10;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortForkJoinTest {


    @Test
    public void quickSortForkJoin() {
        Integer[] array = {3, 2, 1};
        Integer[] expectedArray = {1, 2, 3};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        QuickSortForkJoin<Integer> quickSortForkJoin = new QuickSortForkJoin<>(array);
        forkJoinPool.invoke(quickSortForkJoin);
        forkJoinPool.shutdown();
        assertArrayEquals(array, expectedArray);
    }
}