package com.example.jwt.controller;

import com.example.jwt.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productservice){

        this.productService=productservice;
    }

    @GetMapping("/products")
        public String getTheProducts(){

           String result = productService.getProducts();

            return result;
        }

    }



