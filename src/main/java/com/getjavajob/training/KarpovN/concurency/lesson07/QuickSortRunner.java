package com.getjavajob.training.KarpovN.concurency.lesson07;

import com.getjavajob.training.KarpovN.concurency.StopWatch;
import java.util.Random;

public class QuickSortRunner {

    public static void main(String[] args) throws InterruptedException {
        Integer[] arr = new Integer[1000000];
        Integer[] arr2;
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
         arr2 = arr;
        QuickSortComparable<Integer> sort = new QuickSortComparable<>();
        QuickSortComparableThread<Integer> thr = new QuickSortComparableThread<>(arr, 0, arr.length - 1);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        thr.quicksort(arr);
        System.out.println(stopWatch.getElapsedTime() + " For thread-based in ms...");
        stopWatch.start();
        sort.quicksort(arr2);
        System.out.println(stopWatch.getElapsedTime() + " For one thread in ms...");
    }

}