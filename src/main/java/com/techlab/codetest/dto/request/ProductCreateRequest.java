package com.techlab.codetest.dto.request;

import com.techlab.codetest.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductCreateRequest {
    @NotNull(message = "아이템 ID는 필수 입력값 입니다.")
    private Long itemId;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    @NotBlank(message = "아이템 이름은 필수 입력값 입니다.")
    private String itemName;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    @NotBlank(message = "아이템 이미지는 필수 입력값 입니다.")
    private String itemImage;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    @NotBlank(message = "아이템 URL은 필수 입력값 입니다.")
    private String itemUrl;

    @PositiveOrZero(message = "아이템 원가는 0 이상입니다.")
    private int originalPrice;

    @PositiveOrZero(message = "아이템 판매가는 0 이상입니다.")
    private int salePrice;

    public Product toEntity() {
        return Product.builder()
                .itemId(itemId)
                .itemName(itemName)
                .itemImage(itemImage)
                .itemUrl(itemUrl)
                .originalPrice(originalPrice)
                .salePrice(salePrice)
                .build();
    }
}
