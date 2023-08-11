package com.hackerton.domain.dailyPlanGroup.toDo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ToDoStatus {

    FAIL("실패","F"),
    COMPLETE("성공","C"),
    IN_PROGRESS("진행중","P")
    ;
    private String status;
    private String code;
}
