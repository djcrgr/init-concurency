package com.getjavajob.training.KarpovN.concurency.lesson07;

public class QuickSortComparableThread<T extends Comparable<T>> implements Runnable{

    private T[] array;
    private int low;
    private int high;

    public QuickSortComparableThread(T[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    public <T extends Comparable<T>> void quicksort(T[] array) throws InterruptedException {
        quicksort(array, 0, array.length - 1);
    }

    private  <T extends Comparable<T>> void quicksort(T[] array, int low, int high) throws InterruptedException {
        if (low >= high) {
            return;
        }
        int pi = partition(array, low, high);
        QuickSortComparableThread<T> quickSortComparableThread = new QuickSortComparableThread<>(array, low, pi - 1);
        Thread leftThread = new Thread(quickSortComparableThread);
        leftThread.start();
        QuickSortComparableThread<T> quickSortComparableThread2 = new QuickSortComparableThread<>(array, low, pi - 1);
        Thread rightThread = new Thread(quickSortComparableThread2);
        rightThread.start();
        leftThread.join();
        rightThread.join();
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

    @Override
    public void run() {
        try {
            quicksort(array, low, high);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}