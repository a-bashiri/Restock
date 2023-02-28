package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.DTO.OrderDTO;
import com.example.javabootcampfinalproject.Utility.Enum.Action;
import com.example.javabootcampfinalproject.Model.Orders;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;


    @PostMapping("/create")
    public ResponseEntity createOrder(@AuthenticationPrincipal User user, @RequestBody @Valid OrderDTO orderDTO) {
        ordersService.addOrder(user, orderDTO);
        return ResponseEntity.status(200).body("Order created");
    }

    @GetMapping("/all")
    public ResponseEntity createOrder(@AuthenticationPrincipal User user) {
        List<Orders> ordersList = ordersService.getAllOrdersForUser(user);
        return ResponseEntity.status(200).body(ordersList);
    }

    @PutMapping("/manufacturer/reject/{order_id}")
    public ResponseEntity rejectOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        ordersService.manufacturerActionOnOrder(user, order_id, Action.REJECT);
        return ResponseEntity.status(200).body("Order Rejected");
    }

    @PutMapping("/manufacturer/accept/{order_id}")
    public ResponseEntity acceptOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        ordersService.manufacturerActionOnOrder(user, order_id, Action.ACCEPT);
        return ResponseEntity.status(200).body("Order Accepted");
    }

    @PutMapping("/manufacturer/fulfilled/{order_id}")
    public ResponseEntity fulfillOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        ordersService.manufacturerActionOnOrder(user, order_id, Action.FULFILL);
        return ResponseEntity.status(200).body("Order Accepted");
    }






//    @PostMapping("/all")
//    public ResponseEntity createOrder(@AuthenticationPrincipal User user) {
//        List<Orders> ordersList = ordersService.getAllOrdersForUser(user);
//        return ResponseEntity.status(200).body(ordersList);
//    }
}
