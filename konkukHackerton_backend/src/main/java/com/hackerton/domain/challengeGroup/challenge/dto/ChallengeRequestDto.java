package com.hackerton.domain.challengeGroup.challenge.dto;

import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeStatus;
import com.hackerton.domain.challengeGroup.challenge.entity.ValidationInterval;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChallengeRequestDto {

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    private String validationIntervalCode;
    private int validationCountPerInterval;
    private String categoryCode;

    public Challenge toEntity() {
        return Challenge.builder()
                .title(title)
                .deadline(deadline)
                .validationInterval(ValidationInterval.findByInterval(this.validationIntervalCode))
                .validationCountPerInterval(this.validationCountPerInterval)
                .build();
    }

}
