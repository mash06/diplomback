package com.example.demo.methods;

import java.util.ArrayList;
import java.util.Collections;

public class ZhadniyAlgorithm {
    public int[] w1;
    public int[] p1;
    public String[] name;
    public int maxW = 0;
    public int resW = 0;
    public int resP = 0;
    public double[] ratio = {};
    public ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<String> resname = new ArrayList<>();
    public long time1 = 0;
    public ArrayList<String> str;
    public String [] st;


    public ZhadniyAlgorithm(int[] p, int[] w, int maxW, String[] name) {
        this.p1 = p;
        this.w1 = w;
        this.maxW = maxW;
        this.name = name;
        ratio = new double[w.length];
        str = new ArrayList<>();
        str.add("Расчет соотношения полезности к весу предмета.\n");
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] = (double) this.p1[i] / this.w1[i];
            str.add("Соотношение для " + this.name[i] + ": " + ratio[i] + "\n");
        }
    }

    public ZhadniyAlgorithm() {
    }

    public ZhadniyAlgorithm solve() {
        time1 = System.nanoTime();
        int iter = 0;
        str.add("Будем делать расчеты пока итоговый вес не будет превышать максимальную вместимость ранца\n");
        int i = 1;
        while (resW < maxW) {
            str.add("Шаг " + i + "\n");
            int maxind = maxRatio(ratio, str);

            if (maxind == -1) {
                break;
            }
            str.add("Проверка вместимости текущего предмета:\n");
            if ((resW + w1[maxind]) <= maxW) {
                str.add("- ⠀ Текущий предмет: " + name[maxind] + "\n");
                str.add("- ⠀ Текущий вес ранца: " + resW + "\n");
                str.add("- ⠀ Вес текущего предмета : " + w1[maxind] + "\n");
                str.add("- ⠀ Так как " + resW + " + " + w1[maxind] + " <= " + maxW + ", то добавляем предмет " + name[maxind] + "\n");
                resW += w1[maxind];
                str.add("Текущий вес: " + resW + "\n");
                resP += p1[maxind];
                str.add("Текущая полезность: " + resP + "\n");
                res.add(maxind + 1);
                resname.add(name[maxind]);
                str.add("Наполнение ранца: " + getMas(resname) + "\n");
                str.add("Обнуляем соотношение текущего предмета, чтобы исключить его из дальнейшего рассмотрения\n");
                ratio[maxind] = 0;
            } else {
                str.add("- ⠀ Текущий предмет: " + name[maxind] + "\n");
                str.add("- ⠀ Текущий вес ранца: " + resW + "\n");
                str.add("- ⠀ Вес текущего предмета : " + w1[maxind] + "\n");
                str.add("- ⠀ Так как " + resW + " + " + w1[maxind] + " >= " + maxW + ", то не добавляем предмет " + name[maxind] + "\n");
                break;
            }
            i++;
            iter++;
        }
        Collections.sort(res);
        Collections.sort(resname);
        time1 = (System.nanoTime() - time1);
        ZhadniyAlgorithm zh = new ZhadniyAlgorithm();
        zh.resW = resW;
        zh.resP = resP;
        zh.res = res;
        zh.resname = resname;
        zh.time1 = time1;
        st = new String[str.size()];
        str.toArray(st);
//        System.out.println(iter);
        return zh;
    }

// Вычисление удельной стоимости
    public static int maxRatio(double[] ratio, ArrayList<String> str) {
        double best = 0;
        int ind = -1;
        str.add("Cоотношения: ");
        for (int i = 0; i < ratio.length; i++) {
            str.add(ratio[i] + " ");
            if (ratio[i] > best) {
                best = ratio[i];
                ind = i;
            }
        }
        str.add("\n");
        str.add("Максимальное соотношение: " + best + "\n");

        return ind;
    }

    public String getMas(ArrayList<String> res2) {
        String s1 = "";
        for (String re : res2) {
            s1 += re + " ";
        }
        return s1;
    }

}