package com.hackerton.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFirebaseTokenRequestDto {

    private String firebaseToken;

    public MemberFirebaseTokenRequestDto(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
