package com.techlab.codetest.controller;

import com.techlab.codetest.dto.request.RecCreateRequest;
import com.techlab.codetest.dto.request.RecUpdateRequest;
import com.techlab.codetest.dto.response.ItemResponse;
import com.techlab.codetest.dto.response.RecResponse;
import com.techlab.codetest.service.RecService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recs")
public class RecController {
    private final RecService recService;

    @GetMapping
    public ResponseEntity<ItemResponse> findItems(@RequestParam(name = "id") List<Long> ids) {
        return ResponseEntity.ok().body(recService.findItems(ids));
    }

    @PostMapping
    public ResponseEntity<RecResponse> createRec(
            @RequestBody @Valid RecCreateRequest requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recService.createRec(requestDto));
    }

    @PatchMapping
    public ResponseEntity<RecResponse> updateRec(
            @RequestBody @Valid RecUpdateRequest requestDto) {
        return ResponseEntity.ok(recService.updateRec(requestDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRec(@RequestParam Map<String, String> params) {
        recService.deleteRec(Long.valueOf(params.get("targetItemId")), Long.valueOf(params.get("resultItemId")));
        return ResponseEntity.noContent().build();
    }
}
