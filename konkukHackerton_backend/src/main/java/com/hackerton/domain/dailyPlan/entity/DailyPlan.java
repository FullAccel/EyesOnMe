package com.hackerton.domain.dailyPlan.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.toDoList.dto.ToDoListResponseDto;
import com.hackerton.domain.toDoList.entity.ToDoList;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class DailyPlan extends BaseTimeEntity {

    @Id
    @Column(name = "DAILYPLAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String yearMonth;

    @Column(nullable = false)
    private int date;

    @OneToMany(mappedBy = "dailyPlan")
    List<ToDoList> toDoLists = new ArrayList<>();

    @Builder
    public DailyPlan(Member member, String yearMonth, int date) {
        this.member = member;
        this.yearMonth = yearMonth;
        this.date = date;
    }



    public DailyPlanResponseDto toDailyPlanResponseDto(){
        return DailyPlanResponseDto.builder()
                .yearMonth(yearMonth)
                .date(date)
                .toDoListResponseDtos(
                        toDoLists.stream().map(ToDoListResponseDto::new).toList())
                .build();
    }
}
