package com.hackerton.domain.member.dto;

import com.hackerton.domain.member.entity.Member;
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
    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.profileUrl = entity.getProfileUrl();
        this.firebaseToken = entity.getFirebaseToken();
        this.planSuccessCount = entity.getPlanSuccessCount();
        this.challengeSuccessCount = entity.getChallengeSuccessCount();
    }
}
