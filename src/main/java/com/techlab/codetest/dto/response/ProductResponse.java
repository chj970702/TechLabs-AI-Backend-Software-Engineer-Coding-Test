package com.techlab.codetest.dto.response;

import com.techlab.codetest.entity.Product;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductResponse {
    private String item_id;
    private String item_name;
    private String item_image;
    private String item_url;
    private String original_price;
    private String sale_price;

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .item_id(product.getItemId().toString())
                .item_name(product.getItemName())
                .item_image(product.getItemImage())
                .item_url(product.getItemUrl())
                .original_price(String.valueOf(product.getOriginalPrice()))
                .sale_price(String.valueOf(product.getSalePrice()))
                .build();
    }
}
