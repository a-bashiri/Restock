package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetail, Integer> {

    List<ProductDetail> findProductDetailsByOrder(Integer orderId);
}
