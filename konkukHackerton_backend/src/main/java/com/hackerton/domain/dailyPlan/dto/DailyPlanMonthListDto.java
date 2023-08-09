package com.hackerton.domain.dailyPlan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DailyPlanMonthListDto {

    private List<DailyPlanResponseDto> dailyPlanResponseDtos;

    public DailyPlanMonthListDto(List<DailyPlanResponseDto> dailyPlanResponseDtos) {
        this.dailyPlanResponseDtos = dailyPlanResponseDtos;
    }
}
