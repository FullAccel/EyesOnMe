package com.hackerton.domain.category.dto;

import com.hackerton.domain.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String title;

    private String code;

    private int countByDailyPlan;

    private int countByChallenge;

    private int successDailyPlanCount;

    private int successChallengeCount;

    @Builder
    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.title = category.getCategoryCode().getTitle();
        this.code = category.getCategoryCode().getCode();
        this.countByDailyPlan = category.getCountByToDo();
        this.countByChallenge = category.getCountByChallenge();
        this.successDailyPlanCount = category.getSuccessToDoCount();
        this.successChallengeCount = category.getSuccessChallengeCount();
    }
}
