package io.github.macauyeah.springboot.tutorial.spring_boot_rest_template_test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class ProxyPassControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private RestTemplate restTemplateMock;
    @MockitoBean
    private HttpClient httpClient;

    @Test
    void testSearchEngine() throws Exception {
        Mockito.when(restTemplateMock.getForObject(anyString(), eq(String.class))).thenReturn("FIXRESULT");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/searchEngine/SOMEKEYWORD")
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("FIXRESULT"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testSearchEngineByRawClient() throws Exception {
        Mockito.when(httpClient.send(any(), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(new DummyHttpResponse("fixrawresult"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/rawClientSearchEngine/SOMEKEYWORD")
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value("fixrawresult"))
                .andDo(MockMvcResultHandlers.print());
    }
}
