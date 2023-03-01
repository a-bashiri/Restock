package com.example.javabootcampfinalproject.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {
    @NotNull
    private Integer product_id;
    @NotNull
    private int quantity;
}
