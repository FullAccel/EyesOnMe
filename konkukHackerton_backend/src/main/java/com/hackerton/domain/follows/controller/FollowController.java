package com.hackerton.domain.follows.controller;

import com.hackerton.domain.follows.service.FollowService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{memberId}/{followMemberId}")
    public ResponseEntity<ResultResponse> follow(@PathVariable Long memberId, @PathVariable Long followMemberId) {
        Boolean isFollowSuccess = followService.follow(memberId, followMemberId);
        return ResponseEntity.ok(ResultResponse.of(FOLLOW_SUCCESS, isFollowSuccess));
    }
}
