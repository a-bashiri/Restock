package com.example.javabootcampfinalproject.Repository;

import com.example.javabootcampfinalproject.Model.Orders;
import com.example.javabootcampfinalproject.Repository.OrdersRepository;
import com.example.javabootcampfinalproject.Utility.Enum.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrdersRepositoryTest {
    @Autowired
    OrdersRepository ordersRepository;

    Orders order1;
    Orders order2;



    @BeforeEach
    void setUp(){
        order1 = new Orders(1, OrderStatus.PENDING,250,"Riyadh","Riyadh",
                null,null,null,null,null,null,null);
        order2 = new Orders(2, OrderStatus.PENDING,400,"Riyadh","Riyadh",
                null,null,null,null,null,null,null);
    }


    @Test
    public void findOrdersByIdTest(){
        ordersRepository.save(order1);
        Orders order = ordersRepository.findOrdersById(order1.getId());
        Assertions.assertThat(order).isEqualTo(order1);
    }

    @Test
    public void findOrdersByStatusTest(){
        ordersRepository.save(order1);
        ordersRepository.save(order2);
        List<Orders> orders = ordersRepository.findOrdersByStatus(OrderStatus.PENDING);
        Assertions.assertThat(orders.size()).isEqualTo(2);
    }


}
