package com.example.javabootcampfinalproject.Controller;

import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Utility.Response;
import com.example.javabootcampfinalproject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts(user));
    }
    @PostMapping("/product/add")
    public ResponseEntity<Response> addProduct(@AuthenticationPrincipal User user, @Valid @RequestBody Product product){
        productService.addProduct(user, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Product Added!",201));
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Response> updateManufacturer(@AuthenticationPrincipal User user,@PathVariable Integer id, @Valid @RequestBody Product product){
        productService.updateProduct(user,id,product);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Product Updated!",200));
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@AuthenticationPrincipal User user,@PathVariable Integer id){
        productService.deleteProduct(user,id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Product Deleted!",200));
    }


}
