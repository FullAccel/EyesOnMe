package com.hackerton.domain.challengeGroup.challenge.entity;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hackerton.global.error.ErrorCode.*;

@Getter
@AllArgsConstructor
public enum ValidationInterval {

    ONE_DAY("하루",1, "VI01"),
    ONE_WEEK("일주일", 7, "VI07"),
    TWO_WEEK("이주일", 14, "VI14"),
    ONE_MONTH("한달",31, "VI31"),
    NO_SETTING("설정 안 함",0, "VI00"),

    ;


    private String interval;
    private int dayCount;
    private String code;

    public static ValidationInterval findByInterval(String interval) {
        ValidationInterval validationInterval;
        switch (interval) {
            case "VI01" : return ONE_DAY;
            case "VI07" : return ONE_WEEK;
            case "VI14" : return TWO_WEEK;
            case "VI31" : return ONE_MONTH;
            case "VI00" : return NO_SETTING;
        }
        throw new EntityNotFoundException(VALIDATION_INTERVAL_NOT_FOUND, interval + "은 존재하지 않는 인증 간격입니다");
    }
}
