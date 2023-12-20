package com.example.demo.orders.service;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDto;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.orders.model.Orders;
import com.example.demo.orders.model.OrdersDto;
import com.example.demo.orders.repository.OrdersRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDto;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void create(Integer memberIdx, Integer productIdx) {

        ordersRepository.save(Orders.builder()
                .member(Member.builder().idx(memberIdx).build())
                .product(Product.builder().idx(productIdx).build())
                .build());
    }


    public void delete(Integer idx) {
        ordersRepository.delete(Orders.builder()
                .idx(idx).build());
    }
}
