package com.hackerton.domain.challengeGroup.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeStatus {

    FAIL("실패","F"),
    COMPLETE("성공","C"),
    IN_PROGRESS("진행중","P")
    ;
    private String status;
    private String code;
}
