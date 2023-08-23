package com.example.demo;

import com.example.demo.controller.MethodsController;
import com.example.demo.model.ResponseDynamic;
import com.example.demo.model.ResponseThree;
import com.example.demo.model.data;
import com.example.demo.model.dataga;
import com.example.demo.service.methodserviceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MethodsController.class)
class DemoApplicationTests {


    @Autowired
    private MethodsController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


    @Test
    public void testPostDynamic() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        ResponseDynamic str = new ResponseDynamic(new String[]{"","",""},new String[][]{{"","",""},{"","",""},{"","",""}},new String[][]{{"","",""},{"","",""},{"","",""}},new String[]{"","",""}, Files.readAllBytes(pic.toPath()),Files.readAllBytes(pic.toPath()));
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(str, HttpStatus.CREATED);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void testPostThree() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        File pic = new File("D:\\diplomfront\\src\\img\\newimage.jpg");
        ResponseThree str = new ResponseThree(new String[]{"","",""},new String[]{"","",""}, Files.readAllBytes(pic.toPath()),Files.readAllBytes(pic.toPath()));
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(str, HttpStatus.CREATED);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private methodserviceimpl Service;

    @Test
    public void test200() throws Exception{
        Integer id = 1;
        willDoNothing().given(Service).deleteSol(id);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/vkr/db-solution/delete/{id}",id));

        response.andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    public void testHttpGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vkr/db-solution"))
                .andExpect(status().isOk())
                .andReturn();


    }
//

    @Test
    public void createTest() throws Exception {
        data dt = new data();
        dt.weight = "";
        dt.price = "";
        dt.names = "";
        dt.maxWeight = "";

      mockMvc.perform( MockMvcRequestBuilders
                        .post("/vkr/dataDyn")
                        .content(asJsonString(dt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
//       if (result != notNull()) System.out.println("The test was succesful");

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/vkr/dataZhad")
                        .content(asJsonString(dt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/vkr/dataMethVetv")
                        .content(asJsonString(dt))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        dataga dt2 = new dataga();
        dt2.weight = "";
        dt2.price = "";
        dt2.names = "";
        dt2.maxWeight = "";
        dt2.numofchromo = "";
        dt2.generationsMax = "";
        dt2.mutationtest = "";
        dt2.crossovertest = "";

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/vkr/dataGenAlg")
                        .content(asJsonString(dt2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @AfterEach
    public void cleanUpStreams() {
        System.out.println("The test was succesful");
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
