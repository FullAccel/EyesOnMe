package com.hackerton.domain.dailyPlanGroup.toDo.dto;

import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoUpdateDto {

    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String afterCategoryCode;

    @Builder
    public ToDoUpdateDto(String title, String alarmStartTime, String alarmEndTime, String afterCategoryCode) {
        this.title = title;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.afterCategoryCode = afterCategoryCode;
    }

    public ToDo toEntity(){
        return ToDo.builder()
                .title(this.title)
                .alarmStartTime(this.alarmStartTime)
                .alarmEndTime(this.alarmEndTime)
                .build();
    }
}
