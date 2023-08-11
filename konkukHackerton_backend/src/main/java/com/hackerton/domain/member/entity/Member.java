package com.hackerton.domain.member.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
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

    private String firebaseToken;



    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Follows> followings = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @MapKey(name = "yearMonth")
    private Map<String,DailyPlan> dailyPlans = new HashMap<>();

    @OneToMany(mappedBy = "member")
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Member(String name, String email, String profileUrl, int planSuccessCount, int challengeSuccessCount, String firebaseToken) {
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.planSuccessCount = planSuccessCount;
        this.challengeSuccessCount = challengeSuccessCount;
        this.firebaseToken = firebaseToken;
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

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

}
