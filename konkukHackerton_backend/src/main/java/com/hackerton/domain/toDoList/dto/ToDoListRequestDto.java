package com.hackerton.domain.toDoList.dto;

import com.hackerton.domain.toDoList.entity.ToDoList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoListRequestDto {

    private String title;
    private boolean isComplete;
    private String alarmStartTime;
    private String alarmEndTime;

    @Builder
    public ToDoListRequestDto(String title, boolean is_complete, String alarmStartTime, String alarmEndTime) {
        this.title = title;
        this.isComplete = is_complete;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
    }

    public ToDoList toEntity(){
        return ToDoList.builder()
                .title(this.title)
                .isComplete(this.isComplete)
                .alarmStartTime(this.alarmStartTime)
                .alarmEndTime(this.alarmEndTime)
                .build();
    }
}
