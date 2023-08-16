package com.hackerton.domain.dailyPlanGroup.dailyPlan.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
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

    @Column(nullable = false)
    private String yearMonth;

    @Column(nullable = false)
    private int day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "dailyPlan")
    List<ToDo> toDos = new ArrayList<>();

    @Builder
    public DailyPlan(Member member, String yearMonth, int day) {
        this.member = member;
        this.yearMonth = yearMonth;
        this.day = day;
    }
}
