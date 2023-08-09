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

    @Builder
    public MemberRequestDto(String name, String email, String profileUrl) {
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
    }

    public Member toEntity(){
        return Member.builder()
                .name(this.name)
                .profileUrl(this.profileUrl)
                .email(this.email)
                .build();
    }
}
