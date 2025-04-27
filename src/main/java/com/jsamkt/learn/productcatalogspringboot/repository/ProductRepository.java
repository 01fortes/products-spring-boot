package com.jsamkt.learn.productcatalogspringboot.repository;

import com.jsamkt.learn.productcatalogspringboot.model.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
}
