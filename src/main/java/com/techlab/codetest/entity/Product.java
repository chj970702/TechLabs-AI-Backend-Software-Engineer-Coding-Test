package com.techlab.codetest.entity;

import com.techlab.codetest.dto.request.ProductUpdateRequest;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "products")
@Getter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false, unique = true)
    private Long itemId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_image", nullable = false)
    private String itemImage;

    @Column(name = "item_url", nullable = false)
    private String itemUrl;

    @Column(name = "original_price", nullable = false)
    private int originalPrice;

    @Column(name = "sale_price", nullable = false)
    private int salePrice;

    public void update(ProductUpdateRequest requestDto) {
        updateName(requestDto.getItemName());
        updateImage(requestDto.getItemImage());
        updateUrl(requestDto.getItemUrl());
        updateOriginalPrice(requestDto.getOriginalPrice());
        updateSalePrice(requestDto.getSalePrice());
    }

    private void updateName(String itemName) {
        if (itemName != null) {
            this.itemName = itemName;
        }
    }

    private void updateImage(String itemImage) {
        if (itemImage != null) {
            this.itemImage = itemImage;
        }
    }

    private void updateUrl(String itemUrl) {
        if (itemUrl != null) {
            this.itemUrl = itemUrl;
        }
    }

    private void updateOriginalPrice(Integer originalPrice) {
        if (originalPrice != null) {
            this.originalPrice = originalPrice;
        }
    }

    private void updateSalePrice(Integer salePrice) {
        if (salePrice != null) {
            this.salePrice = salePrice;
        }
    }
}
