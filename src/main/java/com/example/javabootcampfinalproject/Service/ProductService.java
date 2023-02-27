package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.Product;
import com.example.javabootcampfinalproject.Model.User;
import com.example.javabootcampfinalproject.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Product getProduct(Integer productId){
        return productRepository.findProductById(productId);
    }

    public List<Product> getAllProducts(User user){
        return getAllProducts(user.getManufacturer().getId());
    }

    public List<Product> getAllProducts(Integer manufacturerId){
        return productRepository.findProductsByManufacturer_Id(manufacturerId);
    }

    public void addProduct(User user, Product product){
        product.setManufacturer(user.getManufacturer());
        productRepository.save(product);
    }

    public void updateProduct(User user, Integer id, Product product){
        Product oldProduct = productRepository.findProductById(id);
        if (oldProduct == null)
            throw new ApiException("ID Not found",400);
        if (user.getManufacturer().getId() != oldProduct.getManufacturer().getId())
            throw new ApiException("Not authorized to update this product",403);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }
    public void deleteProduct(User user, Integer id){
        Product product = productRepository.findProductById(id);
        if (product == null)
            throw new ApiException("ID Not found",400);
        if (user.getManufacturer().getId() != product.getManufacturer().getId())
            throw new ApiException("Not authorized to delete this product",403);
        productRepository.delete(product);
    }


}
