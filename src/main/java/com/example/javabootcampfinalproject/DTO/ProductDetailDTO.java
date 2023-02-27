package com.example.javabootcampfinalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {
    private Integer product_id;
    private int quantity;
}
