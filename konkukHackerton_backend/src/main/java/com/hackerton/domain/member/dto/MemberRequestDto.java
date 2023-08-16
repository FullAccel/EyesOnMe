package com.hackerton.domain.member.dto;

import com.hackerton.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private String name;

    private String email;

    private String profileUrl;

    private String firebaseToken;

    public Member toEntity(){
        return Member.builder()
                .name(this.name)
                .profileUrl(this.profileUrl)
                .email(this.email)
                .firebaseToken(this.firebaseToken)
                .build();
    }
}
