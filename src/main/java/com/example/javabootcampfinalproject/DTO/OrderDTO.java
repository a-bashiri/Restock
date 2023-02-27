package com.example.javabootcampfinalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Integer manufacturer_id;
    private List<ProductDetailDTO> productDetails;
    private List<Integer> specialRequests_ids;

}
