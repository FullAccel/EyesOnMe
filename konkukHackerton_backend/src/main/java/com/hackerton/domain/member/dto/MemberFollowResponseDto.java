package com.hackerton.domain.member.dto;

import com.hackerton.domain.follows.entity.Follows;
import com.hackerton.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberFollowResponseDto {

    private Long id;

    private String name;

    private String email;

    private String profileUrl;

    public MemberFollowResponseDto(Follows follows) {
        this.id = follows.getFollowingMember().getId();
        this.name = follows.getFollowingMember().getName();
        this.email = follows.getFollowingMember().getEmail();
        this.profileUrl = follows.getFollowingMember().getProfileUrl();
    }

    public MemberFollowResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.profileUrl = member.getProfileUrl();
    }
}
