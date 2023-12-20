package com.example.demo.member.service;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDto;
import com.example.demo.orders.model.Orders;
import com.example.demo.orders.model.OrdersDto;
import com.example.demo.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void create(MemberDto memberDto) {

        memberRepository.save(Member.builder()
                .idx(memberDto.getIdx())
                .password(memberDto.getPassword())
                .email(memberDto.getEmail())
                .build());
    }

    public List<MemberDto> list() {
        List<Member> result = memberRepository.findAll();

        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : result) {
            List<OrdersDto> orderDtos = new ArrayList<>();
            List<Orders> orders = member.getOrders();

            for (Orders order : orders) {
                OrdersDto orderDto = OrdersDto.builder()
                        .idx(order.getIdx())
                        .build();

                orderDtos.add(orderDto);
            }

            MemberDto memberDto = MemberDto.builder()
                    .idx(member.getIdx())
                    .password(member.getPassword())
                    .email(member.getEmail())
//                    .reviews(reviewDtos)
                    .build();
            memberDtos.add(memberDto);
        }


        return memberDtos;
    }

    public MemberDto read(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);
        if (result.isPresent()) {
            Member member = result.get();

            List<OrdersDto> orderDto = new ArrayList<>();

            for (Orders order : member.getOrders()) {
                orderDto.add(OrdersDto.builder()
                        .idx(order.getIdx())
                        .build()
                );
            }

            return MemberDto.builder()
                    .idx(member.getIdx())
                    .password(member.getPassword())
                    .email(member.getEmail())
//                    .reviews(productDtos)
                    .build();
        } else {
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(Member.builder()
                .idx(memberDto.getIdx())
                .password(memberDto.getPassword())
                .email(memberDto.getEmail())
                .build());
    }

    public void delete(Integer id) {
        memberRepository.delete(Member.builder().idx(id).build());
    }
}
