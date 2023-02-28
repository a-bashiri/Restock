package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.DTO.ManufacturerDTO;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Utility.Response;
import com.example.javabootcampfinalproject.Service.OrdersService;
import com.example.javabootcampfinalproject.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrdersService ordersService;


    @PostMapping("/register/manufacturer")
    public ResponseEntity<Response> registerManufacturer(@Valid @RequestBody ManufacturerDTO dto){
        userService.registerManufacturer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Manufacturer registered!",201));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<Response> registerCustomer(@Valid @RequestBody CustomerDTO dto){
        userService.registerCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Customer registered!",201));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Welcome "+user.getRole(),200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteUser(@RequestBody User user){
        userService.deleteUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new Response("User Deleted!",200));
    }

    @PutMapping("/update/manufacturer")
    public ResponseEntity<Response> updateManufacturer(@AuthenticationPrincipal User user,@Valid @RequestBody ManufacturerDTO dto){
        userService.updateManufacturer(user.getId(), dto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Manufacturer Updated!",200));
    }


    @GetMapping("/info")
    public ResponseEntity<User> getInfo(@AuthenticationPrincipal User user){
        User user1 = userService.getInfo(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }

}
