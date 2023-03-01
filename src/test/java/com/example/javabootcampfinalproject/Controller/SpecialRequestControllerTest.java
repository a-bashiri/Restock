package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Service.SpecialRequestsService;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SpecialRequestController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class SpecialRequestControllerTest {
    @MockBean
    SpecialRequestsService specialRequestsService;

    @Autowired
    MockMvc mockMvc;

    SpecialRequest specialRequest;

    @BeforeEach
    void setUp(){
        specialRequest = new SpecialRequest(1,"Sort items",150,null,null);
    }

    @Test
    public void getSpecialRequestTest() throws Exception {
        Mockito.when(specialRequestsService.getSpecialRequest(specialRequest.getId())).thenReturn(specialRequest);
        mockMvc.perform(get("/api/v1/special-request/{id}",specialRequest.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.request").value(specialRequest.getRequest()));
    }
}
