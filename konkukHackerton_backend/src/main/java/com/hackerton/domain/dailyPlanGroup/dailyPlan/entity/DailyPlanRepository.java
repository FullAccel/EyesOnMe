package com.hackerton.domain.dailyPlanGroup.dailyPlan.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    boolean existsDailyPlanByYearMonthAndDate(String yearMonth,int date);

    List<DailyPlan> findByYearMonth(String yearMonth);
}
