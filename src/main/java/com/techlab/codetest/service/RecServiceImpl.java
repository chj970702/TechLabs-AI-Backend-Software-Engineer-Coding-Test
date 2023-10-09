package com.techlab.codetest.service;

import com.opencsv.CSVReader;
import com.techlab.codetest.dto.request.RecCreateRequest;
import com.techlab.codetest.dto.request.RecUpdateRequest;
import com.techlab.codetest.dto.response.ItemResponse;
import com.techlab.codetest.dto.response.ProductResponse;
import com.techlab.codetest.dto.response.RecResponse;
import com.techlab.codetest.entity.Product;
import com.techlab.codetest.entity.Rec;
import com.techlab.codetest.exception.BadRequestException;
import com.techlab.codetest.exception.ConflictException;
import com.techlab.codetest.exception.NotFoundException;
import com.techlab.codetest.repository.ProductRepository;
import com.techlab.codetest.repository.RecRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.techlab.codetest.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class RecServiceImpl implements RecService{
    private final RecRepository recRepository;
    private final ProductRepository productRepository;

    /**
     * rec.csv DB Import
     *
     * 동일 target_item_id와 result_item_id일 경우 중복 데이터로 판단하여 next Line
     */
    @PostConstruct
    @Transactional
    public void uploadRec() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("csv/rec.csv");
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader)) {

            List<Rec> recs = new ArrayList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if (existRecByTargetIdAndResultId(Long.valueOf(line[0]), Long.valueOf(line[1]))) {
                    continue;
                }
                Rec rec = Rec.builder()
                        .targetItemId(Long.valueOf(line[0]))
                        .resultItemId(Long.valueOf(line[1]))
                        .score(Integer.parseInt(line[2]))
                        .rank(Integer.parseInt(line[3]))
                        .build();
                recs.add(rec);
            }
            recRepository.saveAll(recs);
        } catch (Exception e) {
            throw new BadRequestException(INVALID_FILE_EXCEPTION);
        }
    }

    /**
     * 상품 정보 및 연관 상품에 대한 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ItemResponse findItems(List<Long> ids) {
        List<ProductResponse> productResponses = new ArrayList<>();
        List<RecResponse> recResponses = new ArrayList<>();

        for (Long id : ids) {
            Product product = findProductById(id);
            ProductResponse productResponse = ProductResponse.of(product);
            productResponses.add(productResponse);
            List<Rec> recs = findRecsByTargetItemId(id);
            for (Rec rec : recs) {
                Product resultProduct = findProductById(rec.getResultItemId());
                recResponses.add(RecResponse.of(resultProduct, rec));
            }
        }
        ItemResponse itemResponse = ItemResponse.of(productResponses, recResponses);
        return itemResponse;
    }

    /**
     * Insert Rec
     *
     * <p>Prerequisites:
     * <ul>
     *   <li>타겟 상품이 Product DB에 저장되어 있어야 함</li>
     *   <li>연관 상품이 Product DB에 저장되어 있어야 함</li>
     * </ul>
     * </p>
     *
     * @return Rec 객체에 대한 정보와 Target 상품과의 score, rank
     */
    @Override
    @Transactional
    public RecResponse createRec(RecCreateRequest requestDto) {
        if (existRecByTargetIdAndResultId(requestDto.getTargetItemId(), requestDto.getResultItemId())) {
            throw new ConflictException(DUPLICATE_REC_EXCEPTION);
        }
        checkProductById(requestDto.getTargetItemId());
        checkProductById(requestDto.getResultItemId());
        Rec rec = requestDto.toEntity();
        Product product = findProductById(rec.getResultItemId());
        recRepository.save(rec);
        RecResponse recResponse = RecResponse.of(product, rec);
        return recResponse;
    }

    /**
     * Update Rec
     *
     * 동일 상품 및 연관 상품에 대한 score와 rank 값 수정
     * 따로 계산된 score와 rank를 DB에 저장하는 방식
     */
    @Override
    @Transactional
    public RecResponse updateRec(RecUpdateRequest requestDto) {
        checkProductById(requestDto.getTargetItemId());
        checkProductById(requestDto.getResultItemId());
        Rec rec = findRecByTargetIdAndResultId(requestDto.getTargetItemId(), requestDto.getResultItemId());
        Product product = findProductById(rec.getResultItemId());
        rec.update(requestDto);
        RecResponse recResponse = RecResponse.of(product, rec);
        return recResponse;
    }

    /**
     * Delete Rec
     */
    @Override
    @Transactional
    public void deleteRec(Long targetItemId, Long resultItemId) {
        Rec rec = findRecByTargetIdAndResultId(targetItemId, resultItemId);
        recRepository.delete(rec);
    }

    private boolean existRecByTargetIdAndResultId(Long targetItemId, Long resultItemId) {
        return recRepository.existsByTargetItemIdAndResultItemId(targetItemId, resultItemId);
    }
    private List<Rec> findRecsByTargetItemId(Long targetItemId) {
        return recRepository.findAllByTargetItemId(targetItemId)
                .orElseThrow(() -> new NotFoundException(RECS_NOT_FOUND_EXCEPTION));
    }

    private Product findProductById(Long itemId) {
        return productRepository.findByItemId(itemId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
    }

    private Rec findRecByTargetIdAndResultId(Long targetItemId, Long resultItemId) {
        return recRepository.findByTargetItemIdAndResultItemId(targetItemId, resultItemId)
                .orElseThrow(() -> new NotFoundException(REC_NOT_FOUND_EXCEPTION));
    }

    private void checkProductById(Long itemId) {
        if (!productRepository.existsByItemId(itemId)) {
            throw new NotFoundException(PRODUCT_NOT_FOUND_EXCEPTION);
        }
    }
}
