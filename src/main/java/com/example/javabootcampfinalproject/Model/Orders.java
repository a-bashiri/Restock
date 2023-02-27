package com.example.javabootcampfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    private float totalPrice;
    private String sourceLocation;
    private String destinationLocation;
    private Date creationDate;
    private Date ApprovalDate;
    private Date deliverDate;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<SpecialRequest> specialRequests;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<ProductDetail> productDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "user_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id",referencedColumnName = "user_id")
    @JsonIgnore
    private Manufacturer manufacturer;
}
