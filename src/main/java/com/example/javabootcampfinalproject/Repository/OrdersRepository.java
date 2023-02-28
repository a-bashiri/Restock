package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Customer;
import com.example.javabootcampfinalproject.Model.Orders;
import com.example.javabootcampfinalproject.Utility.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Orders findOrdersById(Integer id);
    List<Orders> findOrdersByCustomer_IdAndStatus(Integer id, OrderStatus Status);
    List<Orders> findOrdersByManufacturer_IdAndStatus(Integer id, OrderStatus Status);
    List<Orders> findOrdersByStatus(OrderStatus Status);
    List<Orders> findOrdersByCustomer_Id(Integer id);
    List<Orders> findOrdersByManufacturer_Id(Integer id);

}
