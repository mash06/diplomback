package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class dipdb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    public Integer id;
    public String weight;
    public String price;
    public String maxw;
    public String names;
    public String resw;
    public String resp;
    public String resnab;
    public String method;

    public dipdb(Integer id, String weight, String price, String maxw, String names, String resw, String resp, String resnab, String method) {
        this.id = id;
        this.weight = weight;
        this.price = price;
        this.maxw = maxw;
        this.names = names;
        this.resw = resw;
        this.resp = resp;
        this.resnab = resnab;
        this.method = method;
    }

    public dipdb() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaxw() {
        return maxw;
    }

    public void setMaxw(String maxw) {
        this.maxw = maxw;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getResw() {
        return resw;
    }

    public void setResw(String resw) {
        this.resw = resw;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getResnab() {
        return resnab;
    }

    public void setResnab(String resnab) {
        this.resnab = resnab;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
