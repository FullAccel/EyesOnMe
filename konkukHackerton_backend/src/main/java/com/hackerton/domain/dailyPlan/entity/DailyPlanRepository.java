package com.hackerton.domain.dailyPlan.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    boolean existsDailyPlanByYearMonthAndDate(String yearMonth,int date);

    List<DailyPlan> findByYearMonth(String yearMonth);
}
