package com.example.demo.member.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDto {
    Integer idx;

    String email;
    Integer password;
   /* List<ReviewDto> reviews = new ArrayList<>();*/
}


