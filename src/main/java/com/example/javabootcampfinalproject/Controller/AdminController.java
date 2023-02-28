package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.DTO.ActionDTO;
import com.example.javabootcampfinalproject.Model.SpecialRequest;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Service.OrdersService;
import com.example.javabootcampfinalproject.Service.SpecialRequestsService;
import com.example.javabootcampfinalproject.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final SpecialRequestsService specialRequestsService;
    private final OrdersService ordersService;


    @GetMapping("/user/info/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable Integer id){
        User user = userService.getInfo(id);
        return ResponseEntity.status(200).body(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted!");
    }

    @PostMapping("/special-request/add")
    public ResponseEntity addSpecialRequest(@Valid @RequestBody SpecialRequest specialRequest){
        specialRequestsService.addSpecialRequest(specialRequest);
        return ResponseEntity.status(201).body("Special request added!");
    }


    @PutMapping("/special-request/update/{id}")
    public ResponseEntity updateSpecialRequest(@PathVariable Integer id, @Valid @RequestBody SpecialRequest specialRequest){
        specialRequestsService.updateSpecialRequest(id, specialRequest);
        return ResponseEntity.status(200).body("Special request updated!");
    }

    @DeleteMapping("/special-request/delete/{id}")
    public ResponseEntity deleteSpecialRequest(@PathVariable Integer id){
        specialRequestsService.deleteSpecialRequest(id);
        return ResponseEntity.status(200).body("Special request deleted!");
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal User user, @PathVariable Integer id){
        ordersService.deleteOrder(user, id);
        return ResponseEntity.status(200).body("Order deleted!");
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity adminActionOnOrder(@AuthenticationPrincipal User user, @PathVariable Integer orderId,@Valid @RequestBody ActionDTO action){
        ordersService.adminActionOnOrder(user,orderId,action.action);
        return ResponseEntity.status(200).body("order status changed!");
    }

}
