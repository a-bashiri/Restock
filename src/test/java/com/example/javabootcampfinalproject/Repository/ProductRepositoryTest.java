package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Manufacturer;
import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.ProductRepository;
import com.example.javabootcampfinalproject.Utility.Enum.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    Product product1;
    Product product2;

    @BeforeEach
    void setUp(){
        product1 = new Product(1,"Milk (12 pieces)", 18.3F,null,null);
        product2 = new Product(2,"Yogurt (12 pieces)", 16.5F,null,null);
    }

    @Test
    public void findProductByIdTest(){
        productRepository.save(product1);
        Product product = productRepository.findProductById(product1.getId());
        Assertions.assertThat(product).isEqualTo(product1);
    }




}
