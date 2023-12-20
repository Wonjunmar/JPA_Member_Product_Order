package com.example.demo.product.model;

import com.example.demo.orders.model.Orders;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    Integer price;
    String name;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders = new ArrayList<>();

}
