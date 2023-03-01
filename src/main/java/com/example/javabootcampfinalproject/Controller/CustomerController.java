package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.DTO.CustomerDTO;
import com.example.javabootcampfinalproject.Model.Manufacturer;
import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Service.ManufacturerService;
import com.example.javabootcampfinalproject.Service.ProductService;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final OrdersService ordersService;
    private final ProductService productService;
    private final ManufacturerService manufacturerService;


    @PutMapping("/order/{order_id}/cancel")
    public ResponseEntity cancelOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        ordersService.customerActionOnOrder(user, order_id, Action.CANCEL);
        return ResponseEntity.status(200).body("Order Accepted");
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getManufacturers(){
        return ResponseEntity.status(HttpStatus.OK).body(manufacturerService.getAllManufacturers());
    }
    @GetMapping("/products/manufacturer/{id}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(id));
    }
}
