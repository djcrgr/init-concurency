package com.getjavajob.training.KarpovN.concurency.lesson08;

import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.matrixRead;
import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.matrixWrite;
import static com.getjavajob.training.KarpovN.concurency.lesson08.ParallelThreadsCreator.multiply;

import java.io.IOException;

public class MatrixMultiplicationParallel {

    public static void main(String[] args) throws IOException, InterruptedException {
        int[][] m1 = matrixRead("matrix1.txt");
        int[][] m2 = matrixRead("matrix2.txt");
        int[][] result = new int[m1.length][m2[0].length];
        multiply(m1, m2, result);
        matrixWrite(result);
    }
}