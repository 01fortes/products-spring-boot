package com.jsamkt.learn.productcatalogspringboot.service;

import com.jsamkt.learn.productcatalogspringboot.model.Product;
import com.jsamkt.learn.productcatalogspringboot.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        if (product.getId() != null) {
            return Mono.error(new IllegalArgumentException("Product id cannot be set"));
        }
        return productRepository.save(product);
    }

    public Mono<Product> update(Product product) {
        if (product.getId() == null) {
            return Mono.error(new IllegalArgumentException("Product id cannot be null"));
        }
        var existingProduct = findById(product.getId());
        return existingProduct.flatMap(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());
            existing.setStock(product.getStock());
            return save(existing);
        });
    }

    public Mono<Void> deleteById(Long id) {
        return productRepository.deleteById(id);
    }
}
