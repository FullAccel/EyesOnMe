package com.hackerton.domain.challengeGroup.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.member.dto.MemberValidatorResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChallengeResponseDto {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate deadline;

    private int totalProofNum;

    private int currentSuccessNum;

    private int DDay;

    private double achievementRate;

    private String challengeStatusCode;

    private String validationIntervalCode;

    private int validationCountPerInterval;

    private String categoryCode;

    private List<MemberValidatorResponseDto> validatorList;

    @Builder
    public ChallengeResponseDto(Challenge entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.totalProofNum = entity.getTotalProofNum();
        this.achievementRate = entity.getAchievementRate();
        this.currentSuccessNum = entity.getCurrentSuccessNum();
        this.deadline = entity.getDeadline();
        this.challengeStatusCode = entity.getChallengeStatus().getCode();
        this.validationIntervalCode = entity.getValidationInterval().getCode();
        this.validationCountPerInterval = entity.getValidationCountPerInterval();
        this.DDay = entity.calculateDDAY();
        this.categoryCode = entity.getCategory().getCategoryCode().getCode();
    }


    public ChallengeResponseDto setValidatorList(List<MemberValidatorResponseDto> validatorList) {
        this.validatorList = validatorList;

        return this;
    }
}
