package com.hackerton.domain.dailyPlanGroup.toDo.controller;

import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoRequestDto;
import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoResponseDto;
import com.hackerton.domain.dailyPlanGroup.toDo.dto.ToDoUpdateDto;
import com.hackerton.domain.dailyPlanGroup.toDo.service.ToDoService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long dailyPlanId, @RequestBody ToDoRequestDto toDoRequestDto) {
        Long saveId = toDoService.save(dailyPlanId, toDoRequestDto);

        return ResponseEntity.ok(ResultResponse.of(SAVE_TODO_SUCCESS, saveId));
    }

    @PutMapping("/{toDoId}")
    public ResponseEntity<ResultResponse> update(@PathVariable Long toDoId, @RequestBody ToDoUpdateDto toDoUpdateDto) {
        boolean isUpdate = toDoService.updateById(toDoId, toDoUpdateDto);

        return ResponseEntity.ok(ResultResponse.of(UPDATE_TODO_SUCCESS, isUpdate));
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<ResultResponse> getToDoListById(@PathVariable Long toDoId) {
        ToDoResponseDto findById = toDoService.findById(toDoId);

        return ResponseEntity.ok(ResultResponse.of(GET_TODO_SUCCESS, findById));
    }

    @GetMapping("/{dailyPlanId}/list")
    public ResponseEntity<ResultResponse> getALLToDoListByDailyPlanId(@PathVariable Long dailyPlanId) {
        List<ToDoResponseDto> allByDailyPlanId = toDoService.findAllByDailyPlanId(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_TODO_ALL_SUCCESS, allByDailyPlanId));
    }

    @DeleteMapping("/{toDoId}")
    public ResponseEntity<ResultResponse> delete(@PathVariable Long toDoId) {
        boolean isDelete = toDoService.deleteById(toDoId);

        return ResponseEntity.ok(ResultResponse.of(DELETE_TODO_SUCCESS, isDelete));
    }

    @PostMapping("/complete/{todoId}")
    public ResponseEntity<ResultResponse> completeToDo(@PathVariable Long todoId) {
        boolean isComplete = toDoService.completeToDoById(todoId);
        return ResponseEntity.ok(ResultResponse.of(COMPLETE_TODO, isComplete));
    }


    @PostMapping("/fail/{todoId}")
    public ResponseEntity<ResultResponse> failToDo(@PathVariable Long todoId) {
        boolean isFail = toDoService.failToDoById(todoId);
        return ResponseEntity.ok(ResultResponse.of(FAIL_TODO, isFail));
    }
}
