package com.hackerton.domain.follows.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.follows.dto.FollowResponseByRequestDto;
import com.hackerton.domain.follows.service.FollowService;
import com.hackerton.domain.member.dto.MemberFollowResponseDto;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/request/{memberId}/{followingMemberId}")
    public ResponseEntity<ResultResponse> sendFollowRequest(@PathVariable Long memberId, @PathVariable Long followingMemberId) throws FirebaseMessagingException {
        Boolean isFollowSuccess = followService.sendFollowRequest(memberId, followingMemberId);
        return ResponseEntity.ok(ResultResponse.of(FOLLOW_REQUEST_SUCCESS, isFollowSuccess));
    }

    @DeleteMapping ("/{memberId}/{followingMemberId}")
    public ResponseEntity<ResultResponse> unfollow(@PathVariable Long memberId, @PathVariable Long followingMemberId) {
        boolean isUnfollow = followService.unfollow(memberId, followingMemberId);
        return ResponseEntity.ok(ResultResponse.of(UNFOLLOW_SUCCESS, isUnfollow));
    }

    @GetMapping("followingList/{memberId}")
    public ResponseEntity<ResultResponse> getFollowingList(@PathVariable Long memberId) {
        List<MemberFollowResponseDto> followingList = followService.getFollowingList(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_FOLLOWING_LIST_SUCCESS, followingList));
    }

    @GetMapping("followerList/{memberId}")
    public ResponseEntity<ResultResponse> getFollowerList(@PathVariable Long memberId) {
        List<MemberFollowResponseDto> followingList = followService.getFollowerList(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_FOLLOWER_LIST_SUCCESS, followingList));
    }

}
