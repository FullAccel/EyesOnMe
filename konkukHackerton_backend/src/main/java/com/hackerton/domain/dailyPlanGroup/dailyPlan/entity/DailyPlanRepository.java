package com.hackerton.domain.dailyPlanGroup.dailyPlan.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    boolean existsDailyPlanByYearMonthAndDayAndMemberId(String yearMonth, int day, Long memberId);

    List<DailyPlan> findByYearMonthAndMemberId(String yearMonth, Long memberId);

}
