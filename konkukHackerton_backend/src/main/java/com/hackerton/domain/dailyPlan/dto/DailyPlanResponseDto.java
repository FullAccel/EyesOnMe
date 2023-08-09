package com.hackerton.domain.dailyPlan.dto;

import com.hackerton.domain.toDoList.dto.ToDoListResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DailyPlanResponseDto {

    private String yearMonth;
    private int date;

    private List<ToDoListResponseDto> toDoListResponseDtos;

    @Builder
    public DailyPlanResponseDto(String yearMonth, int date, List<ToDoListResponseDto> toDoListResponseDtos) {
        this.yearMonth = yearMonth;
        this.date = date;
        this.toDoListResponseDtos = toDoListResponseDtos;
    }
}
