package com.example.demo.model;

public class ResponseThree {
    public String[] nabor;
    public String[] resh;
    public byte [] img;
    public byte [] img2;

    public ResponseThree(String[] nabor, String[] resh, byte[] img, byte[] img2) {
        this.nabor = nabor;
        this.resh = resh;
        this.img = img;
        this.img2 = img2;
    }

    public ResponseThree(String[] nabor, byte[] img, byte[] img2) {
        this.nabor = nabor;
        this.img = img;
        this.img2 = img2;
    }
}
