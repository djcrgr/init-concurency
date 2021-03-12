package com.getjavajob.training.KarpovN.concurency.lesson08;

import java.util.ArrayList;
import java.util.List;

public class ParallelThreadsCreator {


    public static void multiply(int[][] matrix1, int[][] matrix2, int[][] result) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        int rows1 = matrix1.length;
        for (int i = 0; i < rows1; i++) {
            MatrixMultiplyRunner task = new MatrixMultiplyRunner(result, matrix1, matrix2, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            waitForThreads(threads);
        }
    }

    private static void waitForThreads(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}