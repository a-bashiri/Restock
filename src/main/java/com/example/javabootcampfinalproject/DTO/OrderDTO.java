package com.example.javabootcampfinalproject.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    @NotNull
    private Integer manufacturer_id;

    @NotNull
    private List<ProductDetailDTO> productDetails;

    @NotNull
    private List<Integer> specialRequests_ids;

    private Integer repeatOrderInDays;


}
