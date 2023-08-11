package com.hackerton.domain.dailyPlanGroup.toDo.service;

import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.category.service.CategoryService;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoRequestDto;
import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoResponseDto;
import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoUpdateDto;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDoRepository;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDoStatus.*;
import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final CategoryService categoryService;

    @Transactional
    public Long save(Long dailyPlanId, ToDoRequestDto toDoRequestDto) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id에 해당하는 데일리 플랜이 없습니다 : " + dailyPlanId));

        ToDo toDo = toDoRequestDto.toEntity();

        Category category = categoryService.save(toDoRequestDto.getCategoryCode(), dailyPlan, dailyPlan.getMember());

        toDo.setDailyPlan(dailyPlan);
        toDo.setCategory(category);

        return toDoRepository.save(toDo).getId();
    }

    @Transactional
    public boolean updateById(Long toDoId, ToDoUpdateDto toDoUpdateDto) {

        ToDo toDo = toDoRepository.findById(toDoId)
                .map(entity -> entity.update(toDoUpdateDto))
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));

        Category category = toDo.getCategory();
        String afterCategoryCode = toDoUpdateDto.getAfterCategoryCode();

        if(!category.getCategoryCode().equals(afterCategoryCode))
            category = categoryService.update(
                    category,
                    afterCategoryCode,
                    toDo.getDailyPlan()
            );
        toDo.setCategory(category);

        return true;
    }

    public ToDoResponseDto findById(Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));


        return ToDoResponseDto.builder()
                .entity(toDo)
                .build();
    }

    public List<ToDoResponseDto> findAllByDailyPlanId(Long dailyPlanId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id의 데일리 플랜이 없습니다 : " + dailyPlanId));

        if(dailyPlan.getToDos().size() == 0)
            throw new EntityNotFoundException(DAILYPLAN_NOT_HAVE_TODOLIST, "해당 Id의 데일리 프랜에 작성된 투두리스트가 없습니다 : " + dailyPlanId);

        return dailyPlan.getToDos().stream()
                .map(ToDoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteById(Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));

        toDoRepository.deleteById(toDoId);

        return true;
    }

    @Transactional
    public boolean completeToDoById(Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));
        toDo.setToDoStatus(COMPLETE);
        toDo.getCategory().completeToDo();
        return true;
    }

    @Transactional
    public boolean failToDoById(Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));
        toDo.setToDoStatus(FAIL);
        toDo.getCategory().completeToDo();
        return true;
    }

}
