package com.example.demo.service;

import com.example.demo.model.*;

import java.io.IOException;
import java.util.List;

public interface methodsservice {
    ResponseDynamic dynamic(String w, String p, String maxw, String names) throws IOException;
    ResponseThree zhadniy(String w, String p, String maxw, String names) throws IOException;
    ResponseThree methVetv(String w, String p, String maxw, String names) throws IOException;
    ResponseThree genAlg(String w, String p, String maxw, String names, String numch, String genmax, String crpr, String mutpr) throws IOException;
    void deleteSol(Integer id);
    dipdb addSol(dipdb kn);
    List<dipdb> findAll();


}
