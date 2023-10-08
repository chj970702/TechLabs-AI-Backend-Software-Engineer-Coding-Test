package com.techlab.codetest.service;

import com.opencsv.CSVReader;
import com.techlab.codetest.dto.request.ProductCreateRequest;
import com.techlab.codetest.dto.request.ProductUpdateRequest;
import com.techlab.codetest.dto.response.ProductResponse;
import com.techlab.codetest.entity.Product;
import com.techlab.codetest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    /**
     * product.csv DB Import
     *
     * 동일 itemId일 경우 중복 데이터로 판단하여 next Line
     */
    @PostConstruct
    @Transactional
    public void uploadProduct() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("csv/product.csv");
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader)) {

            List<Product> products = new ArrayList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (existProductById(Long.valueOf(line[0]))) {
                    continue;
                }
                Product product = Product.builder()
                        .itemId(Long.valueOf(line[0]))
                        .itemName(line[1])
                        .itemImage(line[2])
                        .itemUrl(line[3])
                        .originalPrice(Integer.parseInt(line[4]))
                        .salePrice(Integer.parseInt(line[5]))
                        .build();
                products.add(product);
            }
            productRepository.saveAll(products);
        } catch (Exception e) {
            throw new RuntimeException("Failed to import product.csv");
        }
    }

    /**
     * Insert Product
     */
    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest requestDto) {
        checkProductById(requestDto.getItemId());
        Product product = requestDto.toEntity();
        productRepository.save(product);
        ProductResponse productResponse = ProductResponse.of(product);
        return productResponse;
    }

    /**
     * Update Product
     *
     * null 체크를 통해 요청받은 리소스만 수정
     */
    @Override
    @Transactional
    public ProductResponse updateProduct(ProductUpdateRequest requestDto) {
        Product product = findProductById(requestDto.getItemId());
        product.update(requestDto);
        ProductResponse productResponse = ProductResponse.of(product);
        return productResponse;
    }

    /**
     * Delete Product
     */
    @Override
    @Transactional
    public void deleteProduct(Long itemId) {
        Product product = findProductById(itemId);
        productRepository.delete(product);
    }

    private boolean existProductById(Long itemId) {
        return productRepository.existsByItemId(itemId);
    }

    private void checkProductById(Long itemId) {
        if (productRepository.existsByItemId(itemId)) {
            throw new IllegalArgumentException("중복된 Product 입니다.");
        }
    }

    private Product findProductById(Long itemId) {
        return productRepository.findByItemId(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Product가 존재하지 않습니다."));
    }
}
