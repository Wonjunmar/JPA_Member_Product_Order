package com.example.demo.orders.model;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDto;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrdersDto {
    Integer idx;

    MemberDto memberDto;
    ProductDto productDto;

}
