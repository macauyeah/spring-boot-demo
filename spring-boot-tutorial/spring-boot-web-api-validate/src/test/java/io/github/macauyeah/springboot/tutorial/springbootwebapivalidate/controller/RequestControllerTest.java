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
public class RequestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFirstLevelValidation() throws Exception {
        String request = """
                {"requst":"did you get it?"}
                    """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/postSomething")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());

        request = """
                {"nonNullString":"did you get it?"}
                    """;
        requestBuilder = MockMvcRequestBuilders.post("/api/postSomething")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret.nonNullString")
                        .value("did you get it?"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testMultiLevelValidation() throws Exception {
        // secondLevel can be all null
        String request = """
                {"nonNullString":"did you get it?"}
                    """;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/postSomething")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret.nonNullString")
                        .value("did you get it?"))
                .andDo(MockMvcResultHandlers.print());
        // if secondLevel is existed, secondLevel.nonNullString cannot be null or blank
        request = """
                {
                    "nonNullString":"did you get it?",
                    "secondLevel":{}
                }
                """;
        requestBuilder = MockMvcRequestBuilders.post("/api/postSomething")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());

        request = """
                {
                    "nonNullString":"did you get it?",
                    "secondLevel":{
                        "nonNullString":"got it"
                    }
                }
                """;
        requestBuilder = MockMvcRequestBuilders.post("/api/postSomething")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret.nonNullString")
                        .value("did you get it?"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret.secondLevel.nonNullString")
                        .value("got it"))
                .andDo(MockMvcResultHandlers.print());
    }
}
