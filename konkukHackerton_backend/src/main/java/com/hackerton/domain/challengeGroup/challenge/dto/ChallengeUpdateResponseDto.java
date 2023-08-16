package com.hackerton.domain.challengeGroup.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChallengeUpdateResponseDto {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate deadline;

    private String challengeStatusCode;

    private String validationIntervalCode;

    private int validationCountPerInterval;

    private String categoryCode;


    @Builder
    public ChallengeUpdateResponseDto(Challenge entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.deadline = entity.getDeadline();
        this.challengeStatusCode = entity.getChallengeStatus().getCode();
        this.validationIntervalCode = entity.getValidationInterval().getCode();
        this.validationCountPerInterval = entity.getValidationCountPerInterval();
        this.categoryCode = entity.getCategory().getCategoryCode().getCode();
    }
}
