package com.getjavajob.training.KarpovN.concurency.lesson08;

import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.matrixRead;
import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.matrixWrite;
import static com.getjavajob.training.KarpovN.concurency.lesson08.Matrix.multiply;
import static java.util.Arrays.deepToString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void matrixReadTest() throws IOException {
        assertThat(deepToString(matrixRead("matrix1.txt")),is("[[1, 1, 1], [1, 1, 1], [1, 1, 1]]"));
    }

    @Test
    public void matrixWriteTest() throws IOException {
        int[][] expectedMatrix = matrixRead("matrix1.txt");
        matrixWrite(expectedMatrix);
        assertThat(matrixRead("resultMatrix.txt"), is(expectedMatrix));
    }

    @Test
    public void multiplyTest() throws IOException {
        int[][] matrix1 = matrixRead("matrix1.txt");
        int[][] matrix2 = matrixRead("matrix2.txt");
        assertThat(deepToString(multiply(matrix1, matrix2)), is("[[3, 3, 3], [3, 3, 3], [3, 3, 3]]"));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void exceptionTest() throws IOException {
        int[][] matrix1 = matrixRead("matrixBad1.txt");
        int[][] matrix2 = matrixRead("matrixBad2.txt");
        multiply(matrix1, matrix2);
    }
}