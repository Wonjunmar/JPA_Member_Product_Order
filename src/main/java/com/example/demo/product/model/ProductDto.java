package com.example.demo.product.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {
    Integer idx;
    Integer price;
    String name;

   /* MovieDto movieDto;*/

}
