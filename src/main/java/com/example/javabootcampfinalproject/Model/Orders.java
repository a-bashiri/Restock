package com.example.javabootcampfinalproject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;
    private float totalPrice;
    private String sourceLocation;
    private String destinationLocation;
    private Date creationDate;
    private Date ApprovalDate;
    private Date deliverDate;

    @ManyToMany(mappedBy = "orders")
    private List<SpecialRequest> specialRequests;

    @OneToMany(mappedBy = "order")
    private List<ProductDetail> productDetails;
}
