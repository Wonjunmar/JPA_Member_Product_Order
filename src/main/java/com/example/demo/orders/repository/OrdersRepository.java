package com.example.demo.orders.repository;

import com.example.demo.member.model.Member;
import com.example.demo.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
//    public Optional<Orders> findByIdx(Integer idx);

}
