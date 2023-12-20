package com.example.demo.member.model;

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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    String email;
    Integer password;


    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

}
