package com.techlab.codetest.service;

import com.techlab.codetest.dto.request.RecCreateRequest;
import com.techlab.codetest.dto.request.RecUpdateRequest;
import com.techlab.codetest.dto.response.ItemResponse;
import com.techlab.codetest.dto.response.RecResponse;

import java.util.List;

public interface RecService {
    ItemResponse findItems(List<Long> ids);

    RecResponse createRec(RecCreateRequest requestDto);

    RecResponse updateRec(RecUpdateRequest requestDto);

    void deleteRec(Long targetItemId, Long resultItemId);
}
