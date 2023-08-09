package com.hackerton.domain.member.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.follows.entity.Follows;
import com.hackerton.domain.member.dto.MemberResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String profileUrl;

    private int planSuccessCount;

    private int challengeSuccessCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Follows> followers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @MapKey(name = "yearMonth")
    private Map<String,DailyPlan> dailyPlans = new HashMap<>();

    @Builder
    public Member(String name, String email, String profileUrl) {
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.role = Role.USER;
    }

    public Member update(String name, String picture){
        this.name = name;
        this.profileUrl = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public MemberResponseDto toMemberResponseDto(){
        return MemberResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .profileUrl(this.profileUrl)
                .planSuccessCount(this.planSuccessCount)
                .challengeSuccessCount(this.challengeSuccessCount)
                .build();
    }
}
