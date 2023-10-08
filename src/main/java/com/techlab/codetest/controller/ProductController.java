package com.techlab.codetest.controller;

import com.techlab.codetest.dto.request.ProductCreateRequest;
import com.techlab.codetest.dto.request.ProductUpdateRequest;
import com.techlab.codetest.dto.response.ProductResponse;
import com.techlab.codetest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody @Valid ProductCreateRequest requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(requestDto));
    }

    @PatchMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody @Valid ProductUpdateRequest requestDto) {
        return ResponseEntity.ok(productService.updateProduct(requestDto));
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long itemId) {
        productService.deleteProduct(itemId);
        return ResponseEntity.noContent().build();
    }
}
