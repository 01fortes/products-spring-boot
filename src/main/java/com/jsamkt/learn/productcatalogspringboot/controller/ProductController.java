package com.jsamkt.learn.productcatalogspringboot.controller;

import com.jsamkt.learn.productcatalogspringboot.model.Product;
import com.jsamkt.learn.productcatalogspringboot.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Flux<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/")
    public Mono<Product> update(@RequestBody Product product) {
        return service.save(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}
