package com.hackerton.domain.dailyPlanGroup.toDo.dto;

import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoRequestDto {

    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;

    @Builder
    public ToDoRequestDto(String title, String alarmStartTime, String alarmEndTime, String categoryCode) {
        this.title = title;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.categoryCode = categoryCode;
    }

    public ToDo toEntity(){
        return ToDo.builder()
                .title(this.title)
                .alarmStartTime(this.alarmStartTime)
                .alarmEndTime(this.alarmEndTime)
                .build();
    }
}
