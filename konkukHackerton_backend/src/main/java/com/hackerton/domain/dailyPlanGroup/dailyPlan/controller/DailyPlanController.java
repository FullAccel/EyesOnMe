package com.hackerton.domain.dailyPlanGroup.dailyPlan.controller;

import com.hackerton.domain.dailyPlanGroup.dailyPlan.dto.DailyPlanResponseDto;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.service.DailyPlanService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dailyplan")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;

    @PostMapping("/{memberId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long memberId,@RequestParam("date") String date )
    {
        Long saveId = dailyPlanService.save(memberId, date);
        return ResponseEntity.ok(ResultResponse.of(DAILYPLAN_SAVE_SUCCESS, saveId));
    }

    @GetMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> getDailyPlan(@PathVariable Long dailyPlanId)
    {
        DailyPlanResponseDto dailyPlanResponseDto = dailyPlanService.findById(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_DAILYPLAN_SUCCESS, dailyPlanResponseDto));
    }
    @DeleteMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> delete(@PathVariable Long dailyPlanId)
    {
        boolean isDelete = dailyPlanService.delete(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(DELETE_DAILPLAN_SUCCESS, isDelete));
    }

    @GetMapping("/{memberId}/{yearMonth}")
    public ResponseEntity<ResultResponse> getMonthDailyPlanList(@PathVariable Long memberId, @PathVariable String yearMonth)
    {
        List<DailyPlanResponseDto> monthDailyPlanListByMemberId = dailyPlanService.getMonthDailyPlanListByMemberId(memberId, yearMonth);
        return ResponseEntity.ok(ResultResponse.of(GET_MONTH_DAILYPLAN_SUCCESS, monthDailyPlanListByMemberId));
    }
}
