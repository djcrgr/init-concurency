package com.getjavajob.training.KarpovN.concurency.lesson07;

public class  QuickSortComparable<T extends Comparable<T>> {

    private T[] array;
    private int low;
    private int high;

    public void quicksort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(T[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pi = partition(array, low, high);
        quicksort(array, low, pi - 1);
        quicksort(array, pi + 1, high);
    }

    private <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        int i = low + 1;
        int j = high;
        while (i <= j) {
            if (array[i].compareTo(array[low]) <= 0) {
                i++;
            } else if (array[j].compareTo(array[low]) > 0) {
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

    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}