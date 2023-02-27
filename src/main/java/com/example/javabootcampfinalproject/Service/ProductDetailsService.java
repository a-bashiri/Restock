package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Model.ProductDetail;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    public void addProductDetails(ProductDetail productDetail){
        productDetailsRepository.save(productDetail);
    }
}
