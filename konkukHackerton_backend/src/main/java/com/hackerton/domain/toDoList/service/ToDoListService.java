package com.hackerton.domain.toDoList.service;

import com.hackerton.domain.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.toDoList.dto.ToDoListRequestDto;
import com.hackerton.domain.toDoList.dto.ToDoListResponseDto;
import com.hackerton.domain.toDoList.entity.ToDoList;
import com.hackerton.domain.toDoList.entity.ToDoListRepository;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final DailyPlanRepository dailyPlanRepository;

    @Transactional
    public Long save(Long dailyPlanId, ToDoListRequestDto toDoListRequestDto) {


        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id에 해당하는 데일리 플랜이 없습니다 : " + dailyPlanId));

        ToDoList toDoList = toDoListRequestDto.toEntity();
        toDoList.setDailyPlan(dailyPlan);

        return toDoListRepository.save(toDoList).getId();
    }

    public boolean updateById(Long toDoListId, ToDoListRequestDto toDoListRequestDto) {

        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .map(entity -> entity.update(toDoListRequestDto))
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoListId));

        return true;
    }

    public ToDoListResponseDto findById(Long toDoListId) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoListId));

        return toDoList.toDoListResponseDto();
    }

    public List<ToDoListResponseDto> findAllByDailyPlanId(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id의 데일리 플랜이 없습니다 : " + dailyPlanId));

        if(dailyPlan.getToDoLists().size() == 0)
            throw new EntityNotFoundException(DAILYPLAN_NOT_HAVE_TODOLIST, "해당 Id의 데일리 프랜에 작성된 투두리스트가 없습니다 : " + dailyPlanId);

        return dailyPlan.getToDoLists().stream()
                .map(entity -> entity.toDoListResponseDto())
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteById(Long toDoListId) {
        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoListId));

        toDoListRepository.deleteById(toDoListId);

        return true;
    }

}
