package com.techlab.codetest.dto.request;

import com.techlab.codetest.entity.Rec;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecCreateRequest {
    @NotNull(message = "타겟 아이템 ID는 필수 입력값 입니다.")
    private Long targetItemId;

    @NotNull(message = "연관 아이템 ID는 필수 입력값 입니다.")
    private Long resultItemId;

    @Positive(message = "연관도 점수는 1 이상입니다.")
    private Integer score;

    @Positive(message = "연관도 순위는 1 이상입니다.")
    private Integer rank;

    public Rec toEntity() {
        return Rec.builder()
                .targetItemId(targetItemId)
                .resultItemId(resultItemId)
                .score(score)
                .rank(rank)
                .build();
    }
}
