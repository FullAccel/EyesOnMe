package com.hackerton.domain.toDoList.dto;

import com.hackerton.domain.toDoList.entity.ToDoList;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoListResponseDto {

    private Long id;
    private String title;
    private boolean is_complete;
    private String alarmStartTime;
    private String alarmEndTime;

    @Builder
    public ToDoListResponseDto(ToDoList entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.is_complete = entity.isComplete();
        this.alarmStartTime = entity.getAlarmStartTime();
        this.alarmEndTime = entity.getAlarmEndTime();
    }
}
