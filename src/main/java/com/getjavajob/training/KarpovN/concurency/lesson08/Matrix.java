package com.getjavajob.training.KarpovN.concurency.lesson08;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Matrix {


    public static void main(String[] args) throws IOException {
      int[][] matrix1 = matrixRead("matrix1.txt");
      int[][] matrix2 = matrixRead("matrix2.txt");
      matrixWrite(multiply(matrix1, matrix2));
    }

    public static int[][] matrixRead(String matrixName) throws IOException {
        List<String> lines;
        try (BufferedReader br = new BufferedReader(new FileReader(matrixName))) {
            lines = new ArrayList<>();
            while (br.ready()) {
                lines.add(br.readLine());
            }
        }
        int matrixWidth = lines.get(0).split(" ").length;
        int matrixHeight = lines.size();
        int[][] matrix = new int[matrixHeight][matrixWidth];
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                String[] line = lines.get(i).split(" ");
                matrix[i][j] = parseInt(line[j]);
            }
        }
        return matrix;
    }

    public static void matrixWrite(int[][] matrix) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(
            new FileWriter(
                "resultMatrix.txt"))) {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    String res = anInt + " ";
                    bw.write(res);
                }
                bw.newLine();
            }
        }
    }

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            throw new UnsupportedOperationException();
        }
        int resultRows = matrix1.length;
        int resultColumns = matrix2[0].length;
        int[][] result = new int[resultRows][resultColumns];
        int columns = matrix1[0].length;
        for (int i = 0; i < resultRows; i++) {
            for (int j = 0; j < resultColumns; j++) {
                for (int k = 0; k < columns; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}