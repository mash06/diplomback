package com.example.demo.methods;

import java.util.Comparator;

public class Item {
    public int label;
    public String name;
    public double p;
    public double w;

    public static Comparator<Item> byLabel() {
        return (i1, i2) -> i1.label - i2.label;
    }

    public static Comparator<Item> byRatio() {
        return (i1, i2) -> Double.compare(i2.p /i2.w, i1.p /i1.w);
    }
}
