package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.Utility.Enum.Action;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Service.OrdersService;
import com.example.javabootcampfinalproject.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UserService userService;
    private final OrdersService ordersService;

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody CustomerDTO dto){
        userService.updateCustomer(user.getId(), dto);
        return ResponseEntity.status(HttpStatus.OK).body("Customer Updated!");
    }

    @PutMapping("/order/{order_id}/cancel")
    public ResponseEntity cancelOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        ordersService.customerActionOnOrder(user, order_id, Action.CANCEL);
        return ResponseEntity.status(200).body("Order Accepted");
    }
}
