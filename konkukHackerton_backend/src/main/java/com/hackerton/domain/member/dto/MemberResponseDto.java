package com.hackerton.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String name;

    private String email;

    private String profileUrl;

    private String firebaseToken;

    private int planSuccessCount;

    private int challengeSuccessCount;

    @Builder

    public MemberResponseDto(Long id, String name, String email, String profileUrl, String firebaseToken, int planSuccessCount, int challengeSuccessCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.firebaseToken = firebaseToken;
        this.planSuccessCount = planSuccessCount;
        this.challengeSuccessCount = challengeSuccessCount;
    }
}
