package com.techlab.codetest.entity;

import com.techlab.codetest.dto.request.RecUpdateRequest;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "recs")
@Getter
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_item_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long targetItemId;

    @Column(name = "result_item_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long resultItemId;

    @Column(nullable = false)
    private int score;

    @Column(name = "`rank`", nullable = false)
    private int rank;

    public void update(RecUpdateRequest requestDto) {
        updateScore(requestDto.getScore());
        updateRank(requestDto.getRank());
    }

    private void updateScore(Integer score) {
        if (score != null) {
            this.score = score;
        }
    }

    private void updateRank(Integer rank) {
        if (rank != null) {
            this.rank = rank;
        }
    }
}
