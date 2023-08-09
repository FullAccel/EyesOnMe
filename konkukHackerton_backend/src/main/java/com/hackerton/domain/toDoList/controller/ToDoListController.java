package com.hackerton.domain.toDoList.controller;

import com.hackerton.domain.toDoList.dto.ToDoListRequestDto;
import com.hackerton.domain.toDoList.dto.ToDoListResponseDto;
import com.hackerton.domain.toDoList.service.ToDoListService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todolist")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @PostMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long dailyPlanId, @RequestBody ToDoListRequestDto toDoListRequestDto) {
        Long saveId = toDoListService.save(dailyPlanId, toDoListRequestDto);

        return ResponseEntity.ok(ResultResponse.of(SAVE_TODOLIST_SUCCESS, saveId));
    }

    @PutMapping("/{toDoListId}")
    public ResponseEntity<ResultResponse> update(@PathVariable Long toDoListId, @RequestBody ToDoListRequestDto toDoListRequestDto) {
        boolean isUpdate = toDoListService.updateById(toDoListId, toDoListRequestDto);

        return ResponseEntity.ok(ResultResponse.of(UPDATE_TODOLIST_SUCCESS, isUpdate));
    }

    @GetMapping("/{toDoListId}")
    public ResponseEntity<ResultResponse> getToDoListById(@PathVariable Long toDoListId) {
        ToDoListResponseDto findById = toDoListService.findById(toDoListId);

        return ResponseEntity.ok(ResultResponse.of(GET_TODOLIST_SUCCESS, findById));
    }

    @GetMapping("/{dailyPlanId}/list")
    public ResponseEntity<ResultResponse> getALLToDoListByDailyPlanId(@PathVariable Long dailyPlanId) {
        List<ToDoListResponseDto> allByDailyPlanId = toDoListService.findAllByDailyPlanId(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_TODOLIST_ALL_SUCCESS, allByDailyPlanId));
    }

    @DeleteMapping("/{toDoListId}")
    public ResponseEntity<ResultResponse> delete(@PathVariable Long toDoListId) {
        boolean isDelete = toDoListService.deleteById(toDoListId);

        return ResponseEntity.ok(ResultResponse.of(DELETE_TODOLIST_SUCCESS, isDelete));
    }
}
