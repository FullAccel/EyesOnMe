package com.hackerton.domain.challengeGroup.proof.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProofGetRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;
}
