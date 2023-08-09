package com.hackerton.domain.dailyPlan.service;

import com.hackerton.domain.dailyPlan.dto.DailyPlanMonthListDto;
import com.hackerton.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.hackerton.domain.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.EntityAlreadyExistException;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Long memberId, String yearMonthDate){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        String yearMonth =yearMonthDate.substring(0,6);
        int date = Integer.parseInt(yearMonthDate.substring(6,8));

        if(dailyPlanRepository.existsDailyPlanByYearMonthAndDate(yearMonth, date))
            throw new EntityAlreadyExistException(DAILYPLAN_ALREADY_EXIST, "해당 날짜에 데일리 플랜이 이미 존재합니다, PUT으로 update해주세요 : " + yearMonthDate);

        DailyPlan dailyPlan = DailyPlan.builder()
                .member(member)
                .yearMonth(yearMonth)
                .date(date)
                .build();
        return dailyPlanRepository.save(dailyPlan).getId();
    }

    public DailyPlanResponseDto findById(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 데일리 플랜이 존재하지 않습니다 : " + dailyPlanId));

        return dailyPlan.toDailyPlanResponseDto();

    }

    @Transactional
    public boolean delete(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 데일리 플랜이 존재하지 않아 삭제할 수 없습니다 : " + dailyPlanId));
        dailyPlanRepository.delete(dailyPlan);
        return true;
    }

    public DailyPlanMonthListDto getMonthDailyPlanListByMemberId(Long memberId, String yearMonth) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        List<DailyPlanResponseDto> dailyPlanResponseDtos = dailyPlanRepository.findByYearMonth(yearMonth)
                .stream()
                .map(DailyPlan::toDailyPlanResponseDto)
                .collect(Collectors.toList());

        if(dailyPlanResponseDtos.size() == 0)
            throw new EntityNotFoundException(DAILYPLAN_MONTHLIST_NOT_FOUND, "해당 월에 작성된 데일리 플래너가 없습니다 : " + yearMonth);

        return new DailyPlanMonthListDto(dailyPlanResponseDtos);
    }
}
