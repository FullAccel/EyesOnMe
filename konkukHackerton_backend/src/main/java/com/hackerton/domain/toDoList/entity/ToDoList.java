package com.hackerton.domain.toDoList.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.toDoList.dto.ToDoListRequestDto;
import com.hackerton.domain.toDoList.dto.ToDoListResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ToDoList extends BaseTimeEntity {

    @Id
    @Column(name = "TODOLIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "is_complete")
    private boolean isComplete;

    private String alarmStartTime;
    private String alarmEndTime;

    @ManyToOne
    @JoinColumn(name = "DAILYPLAN_ID")
    private DailyPlan dailyPlan;

    @Builder
    public ToDoList(String title, boolean isComplete, String alarmStartTime, String alarmEndTime, DailyPlan dailyPlan) {
        this.title = title;
        this.isComplete = isComplete;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.dailyPlan = dailyPlan;
    }

    public ToDoList update(ToDoListRequestDto dto){
        this.title = dto.getTitle();
        this.isComplete = dto.isComplete();
        this.alarmStartTime = dto.getAlarmStartTime();
        this.alarmEndTime = dto.getAlarmEndTime();

        return this;
    }

    public ToDoListResponseDto toDoListResponseDto()
    {
        return ToDoListResponseDto.builder()
                .entity(this)
                .build();
    }

    public void setDailyPlan(DailyPlan dailyPlan) {
        this.dailyPlan = dailyPlan;
    }
}
