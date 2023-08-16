package com.hackerton.domain.category.controller;

import com.hackerton.domain.category.dto.CategoryResponseDto;
import com.hackerton.domain.category.service.CategoryService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResultResponse> getCategoryList(@PathVariable Long memberId) {
        List<CategoryResponseDto> categoryListByMemberId = categoryService.getCategoryListByMemberId(memberId);

        return ResponseEntity.ok(ResultResponse.of(GET_CATEGORY_LIST_SUCCESS, categoryListByMemberId));
    }

    @GetMapping("best3/{memberId}")
    public ResponseEntity<ResultResponse> getBest3Category(@PathVariable Long memberId) {
        List<CategoryResponseDto> best3DailyPlanCategory = categoryService.getBest3DailyPlanCategory(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_BEST3_TODO_SUCCESS, best3DailyPlanCategory));
    }

    @GetMapping("worst3/{memberId}")
    public ResponseEntity<ResultResponse> getWorst3Category(@PathVariable Long memberId) {
        List<CategoryResponseDto> worst3DailyPlanCategory = categoryService.getWorst3DailyPlanCategory(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_WORST3_TODO_SUCCESS, worst3DailyPlanCategory));
    }
}
