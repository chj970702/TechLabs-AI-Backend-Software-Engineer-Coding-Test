package com.techlab.codetest.repository;

import com.techlab.codetest.entity.Rec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecRepository extends JpaRepository<Rec, Long> {
    Optional<Rec> findByTargetItemIdAndResultItemId(Long targetItemId, Long resultItemId);
    boolean existsByTargetItemIdAndResultItemId(Long targetItemId, Long resultItemId);
    Optional<List<Rec>> findAllByTargetItemId(Long targetItemId);
}
