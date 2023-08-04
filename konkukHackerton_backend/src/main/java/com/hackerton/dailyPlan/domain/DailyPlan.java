package com.hackerton.dailyPlan.domain;

import com.hackerton.BaseTimeEntity;
import com.hackerton.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DailyPlan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public DailyPlan(String title, Member member) {
        this.title = title;
        this.member = member;
    }
}
