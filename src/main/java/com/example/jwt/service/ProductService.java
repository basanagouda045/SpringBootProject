package com.example.jwt.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

    private final RestClient restClient;

    public ProductService(RestClient restClient){
        this.restClient = restClient;
    }

    public String getProducts(){
        return restClient
                .get()
                .uri("https://dummyjson.com/products?limit=100")
                .retrieve()
                .body(String.class);
    }
}
