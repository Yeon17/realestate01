package com.realestate01.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.request.Request;
import com.realestate01.springboot.domain.request.RequestRepository;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.RequestSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        requestRepository.deleteAll();
    }

    @Test
    public void Request_등록된다() throws Exception{
         RequestSaveRequestDto requestDto=  RequestSaveRequestDto.builder()
                                                .trade("trade")
                                                .addressKindU("addressKindU")
                                                .addressKindD("addressKindD")
                                                .transaction("transaction")
                                                .price("price")
                                                .date("date")
                                                .visit("visit")
                                                .name("name")
                                                .phone("phone")
                                                .message("message")
                                                .build();

        String url = "http://localhost:" + port + "api/v0/request";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Request> all = requestRepository.findAll();
        assertThat(all.get(0).getPhone()).isEqualTo("phone");
        assertThat(all.get(0).getMessage()).isEqualTo("message");

    }



}
