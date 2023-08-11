package com.hackerton.domain.challengeGroup.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationInterval {

    ONE_DAY("하루",1),
    ONE_WEEK("일주일", 7),
    TWO_WEEK("이주일", 14),
    ONE_MONTH("한달",30),
    NO_SETTING("설정 안 함",0),

    ;


    private String interval;
    private int dayCount;
}
