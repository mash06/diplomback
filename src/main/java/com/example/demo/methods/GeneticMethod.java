package com.example.demo.methods;

import java.util.ArrayList;
import java.util.Random;

public class GeneticMethod {
    private final int num;
    private final int[] p;
    private final int[] w;
    private final String[] name;
    private final int capacity;

    private int numofchromo;
    private int generationsMax;
    private double crossovertest;
    private double mutationtest;
    private ArrayList<String> chromosomes;
    private double bestFitness;
    private static String bestsol;
    public long time1 = 0;
    public String [] st;
    public ArrayList<String> str;

    public String getBestsol() {
        return bestsol;
    }

    public String[] getName() {
        return name;
    }
    public GeneticMethod( int[] p, int[] w, int maxW, String [] name, int numofchromo, int generationsMax, double crossovertest, double mutationtest){
        this.num = p.length;
        this.p = p;
        this.w = w;
        this.name = name;
        this.capacity = maxW;

        this.numofchromo = numofchromo;
        this.generationsMax = generationsMax;
        this.crossovertest = crossovertest;
        this.mutationtest = mutationtest;
        chromosomes = new ArrayList<>();
    }

    public void solve(){
        str = new ArrayList<>();
        time1 = System.nanoTime();
        buildChromo(numofchromo);
        time1 = (System.nanoTime() - time1);

        for(int k = 0; k < generationsMax ; k++){
            str.add("Поколение: " + (k+1) + "\n");
            String best = bestSolution();
            bestsol = best;

            newchromo();
            str.add(" " + "Лучшее решение:" + best + " в поколении " + (k+1) + "\n");
            str.add(" " + "Лучшая полезность:" + bestFitness + "\n");

        }

        st = new String[str.size()];
        str.toArray(st);


    }

    private void buildChromo(int n){
        Random rand = new Random();
        StringBuilder chromo;

        for(int i = 0; i < n; i++){
            chromo = new StringBuilder();

            for(int j = 0; j < num; j++){
                int letter = rand.nextInt(2);
                chromo.append(letter);
            }

            chromosomes.add(chromo.toString());
        }
    }


    private double chromoEval(String solution){
        double fit = 0;
        double weight = 0;

        for(int i = 0; i < solution.length(); i++){

            if(solution.charAt(i) == 49){
//                System.out.println(solution.charAt(i));
                weight += w[i];
                fit += p[i];
            }
        }

        if(weight <= capacity)
            return fit;
        else
            return -1;
    }

    private String crossover(String chromo1, String chromo2){
        StringBuilder child = new StringBuilder();
        for(int i = 0; i < chromo1.length(); i++){
            double num = Math.random();
            if(num >= crossovertest) {
                child.append(chromo1.charAt(i));

            }else {
                child.append(chromo2.charAt(i));
            }


        }
        return child.toString();
    }


    private String mutation(String chromo){
        for(int i = 0; i < chromo.length(); i++){
            if(Math.random() <= mutationtest) {
                chromo = swapGene(i, chromo);

            }
        }

        return chromo;
    }

    private String swapGene(int idx, String chromo){
        StringBuilder returnStr = new StringBuilder();
        for(int i = 0; i < chromo.length(); i++){
            if(i == idx){
                if(chromo.charAt(i) == 49)
                    returnStr.append(0);
                else
                    returnStr.append(1);
            }
            else
                returnStr.append(chromo.charAt(i));
        }
        return returnStr.toString();
    }


    private String bestSolution() {
        double bestFit = -1;
        double bestf = -1;
        String bestSol = null;
        int wei = 0;
        int n = 0;
        str.add(" ⠀ Выбор лучшей особи для скрещивания: \n");
        for (String chromoSolution : chromosomes) {
            for (int y = 0; y < this.num; y++) {
                if (chromoSolution.startsWith("1", y)) {
                    wei += w[y];
                    n++;
                }
            }
        }

        double [] mas = new double[n];
        int i = 0;
        double pred = 0;
        for (String chromoSolution : chromosomes) {
            double newFit = chromoEval(chromoSolution);
            for (int y = 0; y < this.num; y++) {
                if (chromoSolution.startsWith("1", y)) {
                    mas[i] = newFit/wei;
                    pred +=newFit/wei;
                }
            }
        }

        for (String chromoSolution : chromosomes) {
            double newFit = chromoEval(chromoSolution);
            if(newFit != -1){
                if (newFit/wei >= bestFit) {
                    bestSol = chromoSolution;
                    bestFit = newFit/wei;
                    bestf = newFit;
                }
            }
        }
        str.add(" ⠀ Особь с лучшей вероятностью:" + bestSol + ", " + String.format("%.6f",bestFit)  +"\n");
        this.bestFitness = bestf;
        return bestSol;
    }
    private void newchromo(){
        String chromo1 = bestSolution();
        chromosomes.remove(chromo1);
        String chromo2 = bestSolution();
        chromosomes = new ArrayList<>();

        chromosomes.add(chromo1);
        for (int i = 1; i < numofchromo; i++) {
            String chromos = mutation(crossover(chromo1, chromo2));
            chromosomes.add(chromos);

        }
    }

}
