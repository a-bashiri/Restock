package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
    Product findProductByName(String name);

    List<Product> findProductsByManufacturer_Id(Integer id);
}
