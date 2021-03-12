package com.getjavajob.training.KarpovN.concurency.lesson08;

import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.matrixRead;
import static com.getjavajob.training.KarpovN.concurency.lesson08.ParallelThreadsCreator.multiply;
import static java.util.Arrays.deepToString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class ParallelThreadsCreatorTest {

    @Test
    public void multiplyTest() throws IOException, InterruptedException {
        int[][] matrix1 = matrixRead("matrix1.txt");
        int[][] matrix2 = matrixRead("matrix2.txt");
        int[][] result = new int[matrix1[0].length][matrix2.length];
        multiply(matrix1, matrix2, result);
        assertThat(deepToString(result), is("[[3, 3, 3], [3, 3, 3], [3, 3, 3]]"));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void exceptionTest() throws IOException, InterruptedException {
        int[][] matrix1 = matrixRead("matrixBad1.txt");
        int[][] matrix2 = matrixRead("matrixBad2.txt");
        int[][] result = new int[matrix1[0].length][matrix2.length];
        multiply(matrix1, matrix2, result);
    }
}