package com.techlab.codetest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400(Bad Request)
    INVALID_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 파일 입니다."),

    // 404(Not Found)
    PRODUCT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "Product가 존재하지 않습니다."),
    RECS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "연관된 상품 정보를 찾을 수 없습니다."),
    REC_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "상품 및 연관 상품 정보를 찾을 수 없습니다."),

    // 409(conflict)
    DUPLICATE_PRODUCT_EXCEPTION(HttpStatus.CONFLICT, "중복된 Product 입니다."),
    DUPLICATE_REC_EXCEPTION(HttpStatus.CONFLICT, "중복된 상품 및 연관 상품 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
