package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;

    Product product1;
    Product product2;

    @BeforeEach
    void setUp(){
        product1 = new Product(1,"Milk (12 pieces)", 18.3F,null,null);
        product2 = new Product(2,"Yogurt (12 pieces)", 16.5F,null,null);
    }

    @Test
    public void getProductTest(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);

        Product product = productService.getProduct(product1.getId());
        Assertions.assertEquals(product1,product);
        verify(productRepository,times(1)).findProductById(product1.getId());
    }
}
