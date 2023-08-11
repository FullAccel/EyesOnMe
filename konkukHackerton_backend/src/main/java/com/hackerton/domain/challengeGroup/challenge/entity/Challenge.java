package com.hackerton.domain.challengeGroup.challenge.entity;

import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Challenge {

    @Id
    @Column(name = "CHALLENGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private ChallengeStatus challengeStatus;

    private int proofMinCount;

    @Enumerated(EnumType.STRING)
    private ValidationInterval validationInterval;

    private int minCountPerInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "challenge")
    List<Proof> proofs = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    List<Validation> validations = new ArrayList<>();

    @Builder
    public Challenge(Member member, LocalDateTime deadline, ChallengeStatus challengeStatus, int proofMinCount, ValidationInterval validationInterval, int minCountPerInterval) {
        this.member = member;
        this.deadline = deadline;
        this.challengeStatus = challengeStatus;
        this.proofMinCount = proofMinCount;
        this.validationInterval = validationInterval;
        this.minCountPerInterval = minCountPerInterval;
    }
}
