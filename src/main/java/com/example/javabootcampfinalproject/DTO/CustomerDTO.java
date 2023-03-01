package com.example.javabootcampfinalproject.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String name;
    @NotNull
    private String location;
}
