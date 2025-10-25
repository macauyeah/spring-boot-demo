package io.github.macauyeah.springboot.tutorial.springbootwebapivalidate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
                // .contentType(MediaType.APPLICATION_JSON)
                // .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }
}
