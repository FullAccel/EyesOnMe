package com.hackerton.member.dto;

import com.hackerton.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String name;

    private String email;

    private String picture;

    private Role role;

    @Builder
    public MemberResponseDto(Long id, String name, String email, String picture,String profileUrl, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
}
