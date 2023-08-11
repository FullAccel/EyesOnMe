package com.hackerton.domain.challengeGroup.challenge.dto;

import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeStatus;
import com.hackerton.domain.challengeGroup.challenge.entity.ValidationInterval;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class ChallengeRequestDto {

    private LocalDateTime deadline;
    private ChallengeStatus challengeStatus;
    private int proofMinCount;
    private String validationInterval;
    private int minCountPerInterval;
}
