package com.example.demo.orders.model;

import com.example.demo.member.model.Member;
import com.example.demo.product.model.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Member_idx")
    Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Product_idx")
    Product product;

}
