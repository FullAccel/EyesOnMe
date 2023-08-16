package com.hackerton.domain.dailyPlanGroup.dailyPlan.service;

import com.hackerton.domain.dailyPlanGroup.dailyPlan.dto.DailyPlanResponseDto;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityAlreadyExistException;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;
    private final MemberRepository memberRepository;

    private final int YEAR_MONTH_START_INDEX = 0;
    private final int DAY_START_INDEX = 6;
    private final int DATE_END_INDEX = 8;

    @Transactional
    public Long save(Long memberId, String yearMonthDay){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        String yearMonth =yearMonthDay.substring(YEAR_MONTH_START_INDEX, DAY_START_INDEX);
        int day = Integer.parseInt(yearMonthDay.substring(DAY_START_INDEX, DATE_END_INDEX));

        if(dailyPlanRepository.existsDailyPlanByYearMonthAndDayAndMemberId(yearMonth, day, memberId))
            throw new EntityAlreadyExistException(DAILYPLAN_ALREADY_EXIST, "해당 날짜에 데일리 플랜이 이미 존재합니다 : " + yearMonthDay);

        DailyPlan dailyPlan = DailyPlan.builder()
                .member(member)
                .yearMonth(yearMonth)
                .day(day)
                .build();
        return dailyPlanRepository.save(dailyPlan).getId();
    }

    public DailyPlanResponseDto findById(Long dailyPlanId) {
        return dailyPlanRepository.findById(dailyPlanId)
                .map(DailyPlanResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 데일리 플랜이 존재하지 않습니다 : " + dailyPlanId));
    }

    @Transactional
    public boolean delete(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 데일리 플랜이 존재하지 않아 삭제할 수 없습니다 : " + dailyPlanId));
        dailyPlanRepository.delete(dailyPlan);
        return true;
    }

    public List<DailyPlanResponseDto> getMonthDailyPlanListByMemberId(Long memberId, String yearMonth) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));


        List<DailyPlanResponseDto> dailyPlanResponseDtos = dailyPlanRepository.findByYearMonthAndMemberId(yearMonth, memberId)
                .stream()
                .map(DailyPlanResponseDto::new)
                .collect(Collectors.toList());

        if(dailyPlanResponseDtos.size() == 0)
            throw new EntityNotFoundException(DAILYPLAN_MONTHLIST_NOT_FOUND, "해당 월에 작성된 데일리 플래너가 없습니다 : " + yearMonth);

        return dailyPlanResponseDtos;
    }
}
