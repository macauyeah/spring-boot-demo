package io.github.macauyeah.springboot.tutorial.springbootwebapidata.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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
    void testReadSomeRecord() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/someRecord/1234")
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").doesNotExist())
                .andDo(MockMvcResultHandlers.print());

        requestBuilder = MockMvcRequestBuilders.get("/api/someRecord/1234")
                .contentType(MediaType.APPLICATION_JSON).with(
                        SecurityMockMvcRequestPostProcessors.user("admin").password("pass").roles("USER", "ADMIN"));
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("your uuid:1234"))
                .andDo(MockMvcResultHandlers.print());
    }
}
