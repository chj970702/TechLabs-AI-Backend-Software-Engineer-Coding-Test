package com.techlab.codetest.repository;

import com.techlab.codetest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByItemId(Long itemId);
    Optional<Product> findByItemId(Long itemId);

}
