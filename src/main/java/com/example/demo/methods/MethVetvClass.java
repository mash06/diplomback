package com.example.demo.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MethVetvClass {

    List<Item> items;
    int maxW;
    public double wM;
    public double pM;
    public long time1 = 0;
    public ArrayList<String> str;
    public String[] st;

    public List<Item> getItems() {
        return items;
    }

    public class Node implements Comparable<Node> {
        public String getMas() {
            String s1 = "";
            for (Item ma : mas) {
                s1 += ma.name + " ";
            }
            return s1;
        }

        public int h;
        List<Item> mas;
        public double granN;
        public double pN;
        public double wN;

        public Node() {
            mas = new ArrayList<>();
        }

        public Node(Node parent) {
            h = parent.h + 1;
            mas = new ArrayList<>(parent.mas);
            granN = parent.granN;
            pN = parent.pN;
            wN = parent.wN;
        }


        public int compareTo(Node other) {
            return (int) (other.granN - granN);
        }

// Вычисление границы текущего узла
        public void granitsa(List<String> str) {
            str.add(" ⠀Вычисление границы текущего узла: \n");
            int i = h;
            double w = wN;
            granN = pN;
            Item item;
            String s1 = " ⠀  ⠀  ⠀ Wb: " + w ;
            String s2 = " ⠀  ⠀  ⠀ Pb: " + pN;

            do {
                item = items.get(i);
                if (w + item.w > maxW) break;
                w += item.w;
                granN += item.p;

                s1 += " + " + item.w;
                s2 += " + " + item.p;
                i++;


            } while (i < items.size());
            if (!s1.equals( " ⠀  ⠀  ⠀ Wb: " + w )) s1 += " = " + w;
            if (!s2.equals( " ⠀  ⠀  ⠀ Pb: " + pN)) s2 += " = " + granN;
            str.add(s1 + "\n");
            str.add(s2+ "\n");
            double g = granN;
            granN += (maxW - w) * (item.p / item.w);
            str.add(" ⠀  ⠀  ⠀ Граница b: " + g + " + (" + maxW +" - " + w + ") * ( " + item.p + "/" + item.w + ") = " + granN + "\n");
        }


    }

    public MethVetvClass(){}

    public MethVetvClass(List<Item> items, int maxW) {
        this.items = items;
        this.maxW = maxW;
    }

    public MethVetvClass solve() {
        time1 = System.nanoTime();
        items.sort(Item.byRatio());
        Node best = new Node();
        Node root = new Node();
        str = new ArrayList<>();


        for (int i = 0;i < items.size(); i++) {
            str.add("Отношение " + items.get(i).name + ": " + items.get(i).p / items.get(i).w + "\n");
        }

        PriorityQueue<MethVetvClass.Node> q = new PriorityQueue<>();
        str.add("Текущий узел: корень");
        root.granitsa(str);
        q.offer(root);
        str.add( "Корень:\n" + "w: "+ root.wN + "\np: " + root.pN + "\nГраница b: " + root.granN + "\n");
        int i = 0;

        while (!q.isEmpty()) {
            MethVetvClass.Node node = q.poll();
            str.add("--- Рассматривается предмет " + items.get(node.h).name + "\n Наполнение ранца: " + node.getMas() + "\n");

            if (node.granN > best.pN && node.h < items.size() - 1) {

                Node with = new Node(node);
                Item item = items.get(node.h);
                with.wN += item.w;

                if (with.wN <= maxW) {
                    str.add("Если предмет взят в ранец:\n ");
                    with.mas.add(items.get(node.h));
                    with.pN += item.p;
                    str.add("Текущий узел: " +  items.get(node.h).name + " " + node.getMas());
                    str.add("\n- ⠀  ⠀ w: "+ with.wN);
                    str.add("\n- ⠀  ⠀ p: " + with.pN);
                    str.add("\n- ⠀  ⠀ Полезность лучшего узла: " + best.pN + "\n");
                    with.granitsa(str);

                    if (with.pN > best.pN) {
                        str.add("Текущий узел (" + items.get(node.h).name + " " + node.getMas() +") становится лучшим, так как его полезность " + with.pN + " больше полезности " + best.pN + "\n");
                        best = with;
                    }

                    if (with.granN > best.pN) {
                        q.offer(with);
                        str.add("Граница узла больше полезности лучшего узла, поэтому добавляем его в дальнейшее рассмотрение. \n" );
                    } else str.add("Граница узла меньше полезности лучшего узла, поэтому далее ничего не рассматриваем. \n" );

                }

                Node without = new Node(node);
                str.add("Если предмет не взят в ранец:\n");
                str.add("Текущий узел: " + node.getMas() );
                str.add("\n ⠀  ⠀ w: "+ without.wN);
                str.add("\n ⠀  ⠀ p: " + without.pN);
                str.add("\n ⠀  ⠀ Полезность лучшего узла: " + best.pN + "\n");
                without.granitsa(str);

                if (without.granN > best.pN) {
                    q.offer(without);
                    str.add("Граница узла больше полезности лучшего узла, поэтому добавляем его в дальнейшее рассмотрение: \n");
                } else str.add("Граница узла меньше полезности лучшего узла, поэтому далее ничего не рассматриваем. \n" );

                i++;
            }  else if (node.granN <= best.pN && node.h >= items.size() - 1) {
                str.add("Высота узла превышает заданную "
                        + " и граница "+ node.granN +  " меньше " + best.pN + ". Поэтому наполнение ранца с текущим предметом не подходит." + "\n");
            } else if(node.granN <= best.pN) {
                str.add("Граница "+ node.granN +  " меньше " + best.pN + ". Поэтому наполнение ранца с текущим предметом не подходит." + "\n");
            } else if (node.h >= items.size() - 1) {
                str.add("Высота узла превышает заданную " + "\n");
            }
            str.add("\n\n");
        }
        time1 = (System.nanoTime() - time1);
        MethVetvClass solution = new MethVetvClass();
        this.pM = best.pN;
        this.wM = best.wN;
        this.items = best.mas;
        this.time1 = time1;
        st = new String[str.size()];
        str.toArray(st);

        return this;
    }

}
