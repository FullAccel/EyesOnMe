package com.hackerton.domain.dailyPlanGroup.dailyPlan.dto;

import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DailyPlanResponseDto {

    private Long dailyPlanId;
    private String yearMonth;
    private int date;


    @Builder
    public DailyPlanResponseDto(DailyPlan entity) {
        this.dailyPlanId = entity.getId();
        this.yearMonth = entity.getYearMonth();
        this.date = entity.getDate();
    }
}
