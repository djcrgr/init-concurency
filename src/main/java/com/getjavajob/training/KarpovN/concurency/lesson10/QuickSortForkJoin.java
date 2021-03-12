package com.getjavajob.training.KarpovN.concurency.lesson10;

import java.util.concurrent.RecursiveAction;

public class QuickSortForkJoin<T extends Comparable<T>> extends RecursiveAction {

    private T[] array;
    private int left;
    private int right;

    public QuickSortForkJoin(T[] array) {
        this.array = array;
        left = 0;
        right = array.length - 1;
    }

    public QuickSortForkJoin(T[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }


    @Override
    protected void compute() {
        if(left < right){
            int pivot = partition(array, left, right);
            new QuickSortForkJoin<>(array,left, pivot).fork();
            new QuickSortForkJoin<>(array, pivot + 1, right).fork();
        }
    }

    private <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        int i = low + 1;
        int j = high;
        while (i <= j) {
            if (array[i].compareTo(array[low]) < 0) {
                i++;
            } else if (array[j].compareTo(array[low]) >= 0) {
                j--;
            } else if (j < i) {
                break;
            } else {
                swap(array, i, j);
            }
        }
        swap(array, low, j);
        return j;
    }

    private <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
