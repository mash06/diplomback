package com.example.demo.model;

public class ActiveMatrix {
    public int[][] matrix;
    public String[][] str;

    public ActiveMatrix(int[][] matrix, String[][] str) {
        this.matrix = matrix;
        this.str = str;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public String[][] getStr() {
        return str;
    }
}
