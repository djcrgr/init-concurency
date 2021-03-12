package com.getjavajob.training.KarpovN.concurency.lesson07;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortComparableThreadTest {

    @Test
    public void quicksort() throws InterruptedException {
        Integer[] array = {3, 2, 1};
        Integer[] expectedArray = {1, 2, 3};
        QuickSortComparableThread<Integer> quickSortComparableThread = new QuickSortComparableThread<>(array, 0, array.length - 1);
        quickSortComparableThread.quicksort(array);
        assertArrayEquals(array, expectedArray);
    }
}