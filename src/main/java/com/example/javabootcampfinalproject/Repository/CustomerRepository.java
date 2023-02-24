package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);
}
