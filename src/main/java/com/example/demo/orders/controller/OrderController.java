package com.example.demo.orders.controller;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDto;
import com.example.demo.orders.model.OrdersDto;
import com.example.demo.orders.service.OrderService;
import com.example.demo.product.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(Integer memberIdx, Integer productIdx) {
        orderService.create(memberIdx,productIdx);

        return ResponseEntity.ok().body("생성");
    }













    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer idx) {
        orderService.delete(idx);
        return ResponseEntity.ok().body("삭제");
                
    }
}
