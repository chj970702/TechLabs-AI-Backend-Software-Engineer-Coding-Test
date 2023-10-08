package com.techlab.codetest.service;

import com.techlab.codetest.dto.request.ProductCreateRequest;
import com.techlab.codetest.dto.request.ProductUpdateRequest;
import com.techlab.codetest.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest requestDto);

    ProductResponse updateProduct(ProductUpdateRequest requestDto);

    void deleteProduct(Long itemId);
}
