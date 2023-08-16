package com.hackerton.domain.dailyPlanGroup.toDo.dto;

import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoResponseDto {

    private Long id;
    private String title;
    private String toDoStatusCode;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;

    @Builder
    public ToDoResponseDto(ToDo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.toDoStatusCode = entity.getToDoStatus().getCode();
        this.alarmStartTime = entity.getAlarmStartTime();
        this.alarmEndTime = entity.getAlarmEndTime();
        this.categoryCode = entity.getCategory().getCategoryCode().getCode();
    }
}
