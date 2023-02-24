package com.example.javabootcampfinalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManufacturerDTO {

    private String username;
    private String password;
    private String phoneNumber;
    private String name;
    private String category;
    private String location;

}
