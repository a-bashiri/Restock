package com.example.javabootcampfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "manufacturer_id",referencedColumnName = "user_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductDetail> productDetailList;
}
