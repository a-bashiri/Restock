package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findOrdersById(Integer id);
}
