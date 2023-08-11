package com.hackerton.domain.follows.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "follows")
public class Follows extends BaseTimeEntity {

    @Id
    @Column(name = "FOLLOW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOW_MEMBER_ID")
    private Member followingMember;

    @Builder
    public Follows(Member member, Member followingMember) {
        this.member = member;
        this.followingMember = followingMember;
    }
}
