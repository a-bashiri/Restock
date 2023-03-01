package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.Model.Orders;
import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Service.OrdersService;
import com.example.javabootcampfinalproject.Service.SpecialRequestsService;
import com.example.javabootcampfinalproject.Service.UserService;
import com.example.javabootcampfinalproject.Utility.Enum.OrderStatus;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AdminController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AdminControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    SpecialRequestsService specialRequestsService;
    @MockBean
    OrdersService ordersService;

    @Autowired
    MockMvc mockMvc;

    User user;
    SpecialRequest specialRequest;

    @BeforeEach
    void setUp(){
        user = new User(1,"abdulaziz","123","053423343", Role.CUSTOMER,null,null);
        specialRequest = new SpecialRequest(1,"Sort items",150,null,null);
    }

    @Test
    public void getUserInfoTest() throws Exception {
        Mockito.when(userService.getInfo(user.getId())).thenReturn(user);
        mockMvc.perform(get("/api/v1/admin/user/info/{id}",user.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/api/v1/admin/user/delete/{id}",user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void addSpecialRequestTest() throws  Exception {
        mockMvc.perform(post("/api/v1/admin/special-request/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(specialRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteSpecialRequestTest() throws Exception{
        mockMvc.perform(delete("/api/v1/admin/special-request/delete/{id}",specialRequest.getId()))
                .andExpect(status().isOk());
    }



}
