package com.example.demo.product.service;

import com.example.demo.orders.model.Orders;
import com.example.demo.orders.model.OrdersDto;
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

    public void create(ProductDto productDto) {

        productRepository.save(Product.builder()
                .idx(productDto.getIdx())
                .price(productDto.getPrice())
                .name(productDto.getName())
                .build());
    }

    public List<ProductDto> list() {
        List<Product> result = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : result) {
            List<OrdersDto> orderDtos = new ArrayList<>();
            List<Orders> orders = product.getOrders();

            for (Orders order : orders) {
                OrdersDto orderDto = OrdersDto.builder()
                        .idx(order.getIdx())
                        .build();

                orderDtos.add(orderDto);
            }

            ProductDto productDto = ProductDto.builder()
                    .idx(product.getIdx())
                    .price(product.getPrice())
                    .name(product.getName())
//                    .reviews(reviewDtos)
                    .build();
            productDtos.add(productDto);
        }


        return productDtos;
    }

    public ProductDto read(Integer idx) {
        Optional<Product> result = productRepository.findById(idx);
        if (result.isPresent()) {
            Product product = result.get();

            List<OrdersDto> orderDto = new ArrayList<>();

            for (Orders order : product.getOrders()) {
                orderDto.add(OrdersDto.builder()
                        .idx(order.getIdx())
                        .build()
                );
            }

            return ProductDto.builder()
                    .idx(product.getIdx())
                    .price(product.getPrice())
                    .name(product.getName())
//                    .reviews(productDtos)
                    .build();
        } else {
            return null;
        }
    }

    public void update(ProductDto productDto) {
        productRepository.save(Product.builder()
                .idx(productDto.getIdx())
                .price(productDto.getPrice())
                .name(productDto.getName())
                .build());
    }

    public void delete(Integer id) {
        productRepository.delete(Product.builder().idx(id).build());
    }
}
