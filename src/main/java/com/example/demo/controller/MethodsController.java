package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.methodsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/vkr")
public class MethodsController {
    private final methodsservice service;
    @Autowired
    public MethodsController(methodsservice service) {
        this.service = service;
    }

    @PostMapping("/dataDyn")
    public ResponseEntity<ResponseDynamic> sendDatadyn(@RequestBody data dt) throws IOException {
        ResponseDynamic str = service.dynamic(dt.weight,dt.price,dt.maxWeight,dt.names);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @PostMapping("/dataZhad")
    public ResponseEntity<ResponseThree> sendDataZhad(@RequestBody data dt) throws IOException {
        ResponseThree str = service.zhadniy(dt.weight,dt.price,dt.maxWeight,dt.names);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @PostMapping("/dataMethVetv")
    public ResponseEntity<ResponseThree> sendDataMethVetv(@RequestBody data dt) throws IOException {
        ResponseThree str = service.methVetv(dt.weight,dt.price,dt.maxWeight,dt.names);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }


    @PostMapping("/dataGenAlg")
    public ResponseEntity<ResponseThree> sendDataGenAlg(@RequestBody dataga dt) throws IOException {
        ResponseThree str = service.genAlg(dt.weight,dt.price,dt.maxWeight,dt.names, dt.numofchromo,dt.generationsMax,dt.crossovertest, dt.mutationtest);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/db-solution/delete/{id}")
    public  ResponseEntity<Integer> deleteSol(@PathVariable("id") Integer id){
        service.deleteSol(id);
        return new ResponseEntity<>(HttpStatus.OK);}

    @PostMapping("/add")
    public ResponseEntity<dipdb> addSol(@RequestBody dipdb solution) {
        dipdb heroo = service.addSol(solution);
        return new ResponseEntity<>(heroo, HttpStatus.CREATED);}

    @GetMapping("/db-solution")
    public ResponseEntity<List<dipdb>> getDbSol(){
        var response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);}

}

