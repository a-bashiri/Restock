package com.example.javabootcampfinalproject.Service;

import com.example.javabootcampfinalproject.DTO.OrderDTO;
import com.example.javabootcampfinalproject.DTO.ProductDetailDTO;
import com.example.javabootcampfinalproject.Exception.ApiException;
import com.example.javabootcampfinalproject.Model.*;
import com.example.javabootcampfinalproject.Repository.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductService productService;


    public void addProductDetails(ProductDetail productDetail){
        productDetailsRepository.save(productDetail);
    }

    public List<ProductDetail> convertToList(OrderDTO orderDTO, Orders order){

        List<ProductDetail> productDetailsList = new ArrayList<ProductDetail>();

        for (ProductDetailDTO productDetailDTO : orderDTO.getProductDetails()){
            Product product = productService.getProduct(productDetailDTO.getProduct_id());
            if (product == null)
                throw new ApiException("Product id was not found", 404);

            if(product.getManufacturer().getId() != orderDTO.getManufacturer_id())
                throw new ApiException("Manufacturer does not own such product", 404);


            ProductDetail productDetail = new ProductDetail(null,
                    productDetailDTO.getQuantity(),
                    product,
                    order);

            addProductDetails(productDetail);
            productDetailsList.add(productDetail);
        }
        return productDetailsList;
    }

    public float calculatePrice(List<ProductDetail> list){

        float totalPrice = 0;
        for (ProductDetail productDetail : list)
            totalPrice += productDetail.getProduct().getPrice() * productDetail.getQuantity();

        return totalPrice;
    }
}
