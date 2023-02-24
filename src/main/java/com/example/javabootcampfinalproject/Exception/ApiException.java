package com.example.javabootcampfinalproject.Exception;


import lombok.Data;

@Data
public class ApiException extends RuntimeException{
    private int status;
    public ApiException(String message,int status){
        super(message);
        this.status = status;
    }
}