package io.github.macauyeah.springboot.tutorial.sprint_boot_rest_template_test.controller;

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
public class ProxyPassControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearchEngine() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/searchEngine/SOMEKEYWORD")
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("SOME PAGES"))
                .andDo(MockMvcResultHandlers.print());
    }
}
