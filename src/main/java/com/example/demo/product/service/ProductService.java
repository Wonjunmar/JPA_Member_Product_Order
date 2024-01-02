package com.example.demo.product.service;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDto;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create (ProductDto productDto) {
        productRepository.save(Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build());
    }

    public List<ProductDto> list() {
        List<Product> result = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : result) {

            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
            productDtos.add(productDto);
        }

        return productDtos;
    }
}
