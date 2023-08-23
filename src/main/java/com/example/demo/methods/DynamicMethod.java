package com.example.demo.methods;

import com.example.demo.model.ActiveMatrix;

import java.util.ArrayList;

public class DynamicMethod {
    public int[] w1;
    public int[] p1;
    public int[][] matr;
    public String[] name;
    public int maxW = 0;
    public int resW = 0;
    public int resP = 0;
    public ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<String> resname = new ArrayList<>();
    public long time1 = 0;
    public String[][] strmat;
    public String[] methstr;

    public ActiveMatrix mat;

    public DynamicMethod(int[] w, int[] p, int mw, String[] name) {
        this.w1 = w;
        this.p1 = p;
        this.maxW = mw;
        this.name = name;
    }


    public DynamicMethod solve() {
        matr = new int[w1.length + 1][maxW + 1];
        time1 = System.nanoTime();
        int k = w1.length;
        int s = maxW;

        mat = matrix(w1, p1, maxW);
        methstr = new String[w1.length + 1];
        meth(k, s, res, mat.getMatrix(), w1, methstr, 0, name);
        strmat = mat.getStr();


        for (Integer i : res) {
            resname.add(name[i - 1]);
            resP += p1[i - 1];
            resW += w1[i - 1];
        }
        time1 = (System.nanoTime() - time1);

        return this;
    }

//Вычисление матрицы
    public ActiveMatrix matrix(int[] w1, int[] p1, int maxW) {
        String[][] str = new String[maxW + 1][w1.length + 1];
        int[][] A = new int[w1.length + 1][maxW + 1];
        for (int k = 0; k <= w1.length; k++) {
            for (int s = 0; s <= maxW; s++) {
                if (k == 0 || s == 0) {
                    A[k][s] = 0;
                    str[s][k] = "V" + k + "(" + s + ") = 0";
                } else {
                    if (s >= w1[k - 1]) {
                        A[k][s] = Math.max(A[k - 1][s], A[k - 1][s - w1[k - 1]] + p1[k - 1]);
                        str[s][k] = "V" + k + "(" + s + ") = max(" + A[k - 1][s] + "," + (A[k - 1][s - w1[k - 1]] + "+" + p1[k - 1]) + ") = " + A[k][s];

                    } else {
                        A[k][s] = A[k - 1][s];
                        str[s][k] = "V" + k + "(" + s + ") = " + A[k - 1][s];

                    }

                }

            }
        }
        String[][] strtransp = new String[w1.length + 1][maxW + 1];
        for (int i = 0; i < strtransp.length; i++) {
            for (int j = 0; j < strtransp[i].length; j++) {
                strtransp[i][j] = str[j][i];
            }
        }

        return new ActiveMatrix(A, strtransp);
    }
// Перевод матрицы в строки
    public String[][] matrixtoString(DynamicMethod d, int[] w, int[] p, int max_w) {
        String[][] s = new String[w1.length + 1][maxW + 1];
        ActiveMatrix mat = d.matrix(w, p, max_w);
        for (int i = 0; i < s.length; i++) {
            s[i][0] = i + "-й шаг ";
            for (int j = 1; j < s[i].length; j++) {
                s[i][j] = mat.getMatrix()[i][j-1] + " ";
            }
        }
        return s;
    }

// Обратный ход метода
    public static void meth(int k, int s, ArrayList<Integer> res, int[][] A, int[] w, String[] str, int i, String[] names) {

        if (A[k][s] == 0) return;

        if (A[k][s] == A[k - 1][s]) {

            str[i] = "Значение " + A[k][s] + " равно " + A[k - 1][s] + ". Сдвигаемся на " + (k - 1) + "-й шаг.";
            i++;
            meth(k - 1, s, res, A, w, str, i, names);

        } else {
            str[i] = "Значение " + A[k][s] + " не равно " + A[k - 1][s] + ". Сдвигаемся на " + (w[k - 1]) + " влево и на " + (k - 1) + "-й шаг. Предмет " + names[k - 1] + " попадает в ранец.";
            i++;
            meth(k - 1, s - w[k - 1], res, A, w, str, i, names);
            res.add(k);
        }

    }
}

