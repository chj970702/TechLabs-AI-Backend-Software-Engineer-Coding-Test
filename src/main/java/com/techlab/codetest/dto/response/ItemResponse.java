package com.techlab.codetest.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemResponse {
    private List<ProductResponse> target;
    private List<RecResponse> results;

    public static ItemResponse of(List<ProductResponse> target, List<RecResponse> results) {
        return ItemResponse.builder()
                .target(target)
                .results(results)
                .build();
    }
}
