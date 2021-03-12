package com.getjavajob.training.KarpovN.concurency.lesson07;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortComparableTest {

    @Test
    public void quicksort() {
        Integer[] array = {3, 2, 1};
        Integer[] expectedArray = {1, 2, 3};
        QuickSortComparable<Integer> quickSortComparable = new QuickSortComparable<>();
        quickSortComparable.quicksort(array);
        assertArrayEquals(array, expectedArray);
    }
}