package com.example.demo.service;

//import com.example.demo.methods.Dynamic;

import com.example.demo.methods.*;
import com.example.demo.model.ResponseDynamic;
import com.example.demo.model.ResponseThree;
import com.example.demo.model.dipdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class methodserviceimpl implements methodsservice {

    @Override
    public ResponseDynamic dynamic(String w, String p, String maxw, String names) throws IOException {
        List<String> wei = new ArrayList<String>(Arrays.asList(w.split(" ")));
        List<String> maxi = new ArrayList<String>(Arrays.asList(maxw.split(" ")));
        int max = Integer.parseInt(maxi.get(0));

        List<String> pri = new ArrayList<String>(Arrays.asList(p.split(" ")));
        List<String> nm = new ArrayList<String>(Arrays.asList(names.split(" ")));
        int[] w1 = new int[wei.size()];
        int[] p1 = new int[pri.size()];
        String[] n1 = new String[nm.size()];
        for (int i = 0; i <wei.size() ; i++) {
            w1[i] = Integer.parseInt(wei.get(i));
            p1[i] = Integer.parseInt(pri.get(i));
            n1[i] = nm.get(i);
        }
        DynamicMethod d = new DynamicMethod(w1,p1,max,n1);
        d.solve();
        String [] s = {"","",""};
        String [][] table = new String[d.matrixtoString(d,w1,p1,max).length + 1][d.matrixtoString(d,w1,p1,max)[0].length];
        String [][] table1 = d.matrixtoString(d,w1,p1,max);
        for (int i = 0; i <table[0].length ; i++) {
            table[0][i] = i + " ";
        }
        for (int i = 1; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = table1[i-1][j];
            }
        }
        String [][] resh = d.strmat;
        String [] obr = d.methstr;

        int i = 0;
        for (String item: d.resname) {
            s[0] += item + " ";
        }
        s[1] = d.resP + " " ;
        s[2] = d.resW + " ";

        Rect.paint(w1,n1,max,d.res,d.resname, d.maxW, d.resW);

        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        byte[] byteContent = Files.readAllBytes(pic.toPath());

        File pic2 = new File("D:\\diplomfront\\src\\img\\newimage2.jpg");
        byte[] byteContent2 = Files.readAllBytes(pic2.toPath());

        return new ResponseDynamic(s,resh,table,obr,byteContent,byteContent2);
    }

    @Override
    public ResponseThree zhadniy(String w, String p, String maxw, String names) throws IOException {
    List<String> wei = new ArrayList<String>(Arrays.asList(w.split(" ")));
        List<String> maxi = new ArrayList<String>(Arrays.asList(maxw.split(" ")));
        int max = Integer.parseInt(maxi.get(0));

        List<String> pri = new ArrayList<String>(Arrays.asList(p.split(" ")));
        List<String> nm = new ArrayList<String>(Arrays.asList(names.split(" ")));
        int[] w1 = new int[wei.size()];
        int[] p1 = new int[pri.size()];
        String[] n1 = new String[nm.size()];
        for (int i = 0; i <wei.size() ; i++) {
            w1[i] = Integer.parseInt(wei.get(i));
            p1[i] = Integer.parseInt(pri.get(i));
            n1[i] = nm.get(i);
        }
        ZhadniyAlgorithm d = new ZhadniyAlgorithm(p1,w1,max,n1);
        d.solve();
        int i = 0;
        String [] s = {"","",""};
        String [] resh = d.st;

        for (String item: d.resname) {
            s[0] += item + " ";
        }
        s[1] = d.resP + " " ;
        s[2] = d.resW + " ";
        System.out.println(Arrays.toString(s));


        Rect.paint(w1,n1,max,d.res,d.resname, d.maxW, d.resW);
        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        byte[] byteContent = Files.readAllBytes(pic.toPath());
        File pic2 = new File("D:\\diplomfront\\src\\img\\newimage2.jpg");
        byte[] byteContent2 = Files.readAllBytes(pic2.toPath());


        return new ResponseThree(s,resh,byteContent,byteContent2);
    }

    @Override
    public ResponseThree methVetv(String w, String p, String maxw, String names) throws IOException {
        List<String> wei = new ArrayList<String>(Arrays.asList(w.split(" ")));
        List<String> maxi = new ArrayList<String>(Arrays.asList(maxw.split(" ")));
        int max = Integer.parseInt(maxi.get(0));

        List<String> pri = new ArrayList<String>(Arrays.asList(p.split(" ")));
        List<String> nm = new ArrayList<String>(Arrays.asList(names.split(" ")));
        int[] w1 = new int[wei.size()];
        int[] p1 = new int[pri.size()];
        String[] n1 = new String[nm.size()];
        for (int i = 0; i <wei.size() ; i++) {
            w1[i] = Integer.parseInt(wei.get(i));
            p1[i] = Integer.parseInt(pri.get(i));
            n1[i] = nm.get(i);
        }

        List<Item> items = new LinkedList<Item>();
        for (int j = 0; j < w1.length; j++) {
            Item item = new Item();
            item.label = j + 1;
            item.name = n1[j];
            item.p = p1[j];
            item.w = w1[j];
            items.add(item);
        }
        MethVetvClass d = new MethVetvClass(items,max);
        d.solve();
        String [] s = {"","",""};
        int i = 0;
        d.getItems().sort(Item.byLabel());
        for (int j = 0; j < d.getItems().size(); j++) {
            s[0] += d.getItems().get(j).name + " ";
            System.out.println(d.getItems().get(j).name);
        }
        String [] resh = d.st;

        s[1] = d.pM + " " ;
        s[2] = d.wM + " ";
        ArrayList<Integer> nab = new ArrayList<>(n1.length);
        ArrayList<String> nabname = new ArrayList<>(d.getItems().size());
        for (int j = 0; j < n1.length; j++) {
            for (int k = 0; k < d.getItems().size(); k++) {
                if (Objects.equals(d.getItems().get(k).name, n1[j])) nab.add(j+1);
            }

        }
        for (int j = 0; j < d.getItems().size(); j++) {
            nabname.add(d.getItems().get(j).name);
        }

        Rect.paint(w1,n1,max,nab,nabname, max,(int)d.wM);
//        String s1 = "/img/newimage.jpg";
        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        byte[] byteContent = Files.readAllBytes(pic.toPath());

        File pic2 = new File("D:\\diplomfront\\src\\img\\newimage2.jpg");
        byte[] byteContent2 = Files.readAllBytes(pic2.toPath());

        return new ResponseThree(s,resh, byteContent,byteContent2);
    }



    @Override
    public ResponseThree genAlg(String w, String p, String maxw, String names, String numch, String genmax, String crpr, String mutpr) throws IOException {
        List<String> wei = new ArrayList<String>(Arrays.asList(w.split(" ")));
        List<String> maxi = new ArrayList<String>(Arrays.asList(maxw.split(" ")));
        int max = Integer.parseInt(maxi.get(0));

        List<String> pri = new ArrayList<String>(Arrays.asList(p.split(" ")));
        List<String> nm = new ArrayList<String>(Arrays.asList(names.split(" ")));
        List<String> nc = new ArrayList<String>(Arrays.asList(numch.split(" ")));
        int numchr = Integer.parseInt(nc.get(0));
        List<String> gm = new ArrayList<String>(Arrays.asList(genmax.split(" ")));
        int gen = Integer.parseInt(gm.get(0));
        List<String> cp = new ArrayList<String>(Arrays.asList(crpr.split(" ")));
        double probcr = Double.parseDouble(cp.get(0));
        List<String> mp = new ArrayList<String>(Arrays.asList(mutpr.split(" ")));
        double probmut = Double.parseDouble(mp.get(0));

        int[] w1 = new int[wei.size()];
        int[] p1 = new int[pri.size()];
        String[] n1 = new String[nm.size()];
        for (int i = 0; i <wei.size() ; i++) {
            w1[i] = Integer.parseInt(wei.get(i));
            p1[i] = Integer.parseInt(pri.get(i));
            n1[i] = nm.get(i);
        }


        GeneticMethod d = new GeneticMethod(p1,w1,max,n1,numchr,gen,probcr,probmut);
        d.solve();
        String [] resh = d.st;


        int k;
        ArrayList<String> sl = new ArrayList<>();
        ArrayList<Integer> slint = new ArrayList<>();
        int weight = 0;
        int price = 0;
        for(int y = 0; y < w1.length; y++) {
            if(d.getBestsol().startsWith("1", y)) {
                k = y;
                weight += w1[k];
                price += p1[k];
                sl.add(d.getName()[k] + " ");
                System.out.println(y);
                slint.add(y + 1);

            }
        }
        String [] s = {"","",""};

        for(int i = 0; i < sl.size(); i++) {
            s[0] += sl.get(i) + " ";
        }


        s[1] = price + " " ;
        s[2] = weight + " ";
        System.out.println(s[1]);
        System.out.println(s[2]);
        System.out.println(Arrays.toString(s));

//        String [] resh = d.st;

        Rect.paint(w1,n1,max,slint,sl, max, weight);
//        String s1 = "/img/newimage.jpg";
        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        byte[] byteContent = Files.readAllBytes(pic.toPath());

        File pic2 = new File("D:\\diplomfront\\src\\img\\newimage2.jpg");
        byte[] byteContent2 = Files.readAllBytes(pic2.toPath());

//        return new ResponseZhad(s,resh,byteContent,byteContent2);



//        s[d.resname.size()+1] = "Итоговый вес ранца: " + d.resW;
//        s[d.resname.size()+2] = "Итоговая полезность ранца: " + d.resP;
        return new ResponseThree(s,resh,byteContent,byteContent2);
    }

    private final RepInt knap;

    @Autowired
    public methodserviceimpl(RepInt knap) {
        this.knap = knap;
    }

    @Override
    public dipdb addSol(dipdb kn){
        return knap.save(kn);
    }

    @Override
    public void deleteSol (Integer id){
        knap.deleteSolById(id);
    }

    @Override
    public List<dipdb> findAll() {
        return knap.findAll();
    }


}
