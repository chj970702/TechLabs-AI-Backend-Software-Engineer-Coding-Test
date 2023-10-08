package com.techlab.codetest.dto.response;

import com.techlab.codetest.entity.Product;
import com.techlab.codetest.entity.Rec;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecResponse {
    private String item_id;
    private String item_name;
    private String item_image;
    private String item_url;
    private String original_price;
    private String sale_price;
    private String score;
    private String rank;

    public static RecResponse of(Product product, Rec rec) {
        return RecResponse.builder()
                .item_id(rec.getResultItemId().toString())
                .item_name(product.getItemName())
                .item_image(product.getItemImage())
                .item_url(product.getItemUrl())
                .original_price(String.valueOf(product.getOriginalPrice()))
                .sale_price(String.valueOf(product.getSalePrice()))
                .score(String.valueOf(rec.getScore()))
                .rank(String.valueOf(rec.getRank()))
                .build();
    }
}
