package com.hackerton.domain.challengeGroup.validation.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.global.status.ProgressStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Validation extends BaseTimeEntity {

    @Id
    @Column(name = "VALIDATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String validatorName;

    @Enumerated(EnumType.STRING)
    private ProgressStatus validationStatus;

    @ManyToOne
    @JoinColumn(name = "CREATOR_MEMBER_ID")
    private Member creatorMember;

    @ManyToOne
    @JoinColumn(name = "VALIDATOR_MEMBER_ID")
    private Member validatorMember;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @Builder
    public Validation(String validatorName, Member creatorMember, Member validatorMember, Challenge challenge) {
        this.validatorName = validatorName;
        this.validationStatus = ProgressStatus.IN_PROGRESS;
        this.creatorMember = creatorMember;
        this.validatorMember = validatorMember;
        this.challenge = challenge;

    }

    public void setValidationStatus(ProgressStatus achievementCheck) {
        this.validationStatus = achievementCheck;
    }
}
