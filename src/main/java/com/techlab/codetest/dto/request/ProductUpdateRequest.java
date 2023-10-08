package com.techlab.codetest.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductUpdateRequest {
    @NotNull(message = "아이템 ID는 필수 입력값 입니다.")
    private Long itemId;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    private String itemName;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    private String itemImage;

    @Length(max = 255, message = "255자 이내로 입력해주세요.")
    private String itemUrl;

    @PositiveOrZero(message = "아이템 원가는 0 이상입니다.")
    private Integer originalPrice;

    @PositiveOrZero(message = "아이템 판매가는 0 이상입니다.")
    private Integer salePrice;
}
