package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorHandlingTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHtmlResourceNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assets/notexists.js");
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testForceError() throws Exception {
        RequestBuilder ioRequestBuilder = MockMvcRequestBuilders.get("/api/ioError");
        assertThrows(IOException.class, ()->{
            this.mockMvc.perform(ioRequestBuilder);// test case cannot get the response ???
            // because of the unhandle exception?
        });
        
        RequestBuilder forceErrorBuilder = MockMvcRequestBuilders.get("/api/forceError");
        this.mockMvc.perform(forceErrorBuilder)
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("masked message"));


        RequestBuilder authErrorBuilder = MockMvcRequestBuilders.get("/api/authError");
        this.mockMvc.perform(authErrorBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("custom auth error"));
    }
}
