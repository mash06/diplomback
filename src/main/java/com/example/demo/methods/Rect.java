package com.example.demo.methods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Rect {
    public static void paint(int [] arr, String [] names, int len, ArrayList<Integer> nabor, ArrayList<String> resname, int maxw, int resW){
        Color [] cl = {new Color(255,239,145),new Color(128,128,128),new Color(122,142,211),new Color(115,206,113), new Color(184,47,47), new Color(133,64,25)};
        ArrayList<Color> clch = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length ; i++) {
            clch.add(i,getRandom(cl));
        }

        int h2 = arr.length * (30 + 10);

        ArrayList<Rectangle2D.Double> rects = new ArrayList<>(arr.length);
        double x = 100d;
        double y = 30d;

        double height = 30d;
        BufferedImage bufferedImage = new BufferedImage(700, h2 + 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font f1 = new Font("Montserrat", Font.BOLD, 12);
        g2d.setPaint(Color.white);
        g2d.fill(new Rectangle2D.Double(0, 0, 700, h2 + 100));

        for (int i = 0; i < arr.length; i++) {
            Rectangle2D.Double rect = new Rectangle2D.Double(x, y, ((double) arr[i] /len)*200d, height);
            rects.add(i,rect);
            g2d.setPaint(clch.get(i));
            g2d.setFont(f1);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(names[i],(float) x + ((float) arr[i] /len)*200 + 20 ,(float)y + 18);
            g2d.fill(rects.get(i));
            g2d.setColor(Color.black);
            g2d.draw(rects.get(i));
//            x += ((double) arr[i] /len)*200d + 20d;
            y += height + 10d;

        }

        Rectangle2D.Double rect = new Rectangle2D.Double(95d, y + 20d, 210d, 40d);
        g2d.setPaint(Color.GRAY);
        g2d.fill(rect);
        g2d.setColor(Color.black);
        g2d.draw(new Rectangle2D.Double(100d,y + 25d,200d,30d));
        g2d.drawString("Ранец, вместимость " + maxw,350,(int)y + 40);

        RenderedImage rendImage = bufferedImage;

        File file = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        try {
            ImageIO.write(rendImage, "jpg", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        BufferedImage bufferedImage2 = new BufferedImage(700, 150, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d2 = bufferedImage2.createGraphics();
        g2d2.setPaint(Color.white);
        g2d2.fill(new Rectangle2D.Double(0, 0, 700, 150));

        double per2 = (double) resW /maxw * 100;
        System.out.println((int)per2);

        int [] arr2 = new int[nabor.size()];
        System.out.println(arr2.length);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = nabor.get(i);
        }
        System.out.println(Arrays.toString(arr2));
        int [] ind2 = new int[arr2.length];
        ArrayList<Integer> ind = new ArrayList<>(arr2.length);

        for(int i = 0; i < arr.length; i++){
            for (int k : arr2) {
                ind.add(arr[k-1]);
            }
        }

        for (int i = 0; i < arr2.length ; i++) { ind2[i] = ind.get(i);}

        Rectangle2D.Double rectall = new Rectangle2D.Double(95d, 30d, 210d, 40d);
        g2d2.setPaint(Color.GRAY);
        g2d2.fill(rectall);
        g2d2.setColor(Color.black);
        g2d2.draw(new Rectangle2D.Double(100d,35d,200d,30d));

        double x1 = 100d ;
        double y1 = 35d;
        double height1 = 30d;

        for (int i = 0; i < arr2.length ; i++) {
            Rectangle2D.Double rect2 = new Rectangle2D.Double(x1, y1, ((double) ind2[i] / len) * 200d, height1);
            g2d2.setPaint(clch.get(nabor.get(i)-1));
            g2d2.setFont(f1);
            g2d2.drawString(resname.get(i),(float) x1 + (float)(((double) ind2[i] / len) * 200d) /2  ,(float)y1 + 45);
            g2d2.fill(rect2);
            g2d2.setColor(Color.black);
            g2d2.draw(rect2);
            x1 += ((double) ind2[i] / len) * 200d;

        }

        g2d2.drawString("Заполненность ранца  " + (int)per2 + "%",(int) x1 + 70,(int) y1 + 20);


        RenderedImage rendImage2 = bufferedImage2;
        File file2 = new File("D:\\diplomfront\\src\\img\\newimage2.jpg");
        try {
            ImageIO.write(rendImage2, "jpg", file2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Color getRandom(Color[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
