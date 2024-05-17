package io.github.macauyeah.springboot.tutorial.springbootwebapibasic.controller;

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
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSomeRecord() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/someRecord/1234")
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("your uuid:1234"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testPostSomeRecord() throws Exception {
        String request = """
                {"requst":"did you get it?"}
                    """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/someRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.requst").value("did you get it?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("got your request"))
                .andDo(MockMvcResultHandlers.print());
    }
}
