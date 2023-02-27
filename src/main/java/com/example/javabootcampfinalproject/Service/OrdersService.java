package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    public OrderDTO getDto(OrderDTO dto){
        return dto;
    }
}
