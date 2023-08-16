package com.hackerton.domain.challengeGroup.challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ValidatorRequestDto {
    List<String> validatorNameList = new ArrayList<>();

    public ValidatorRequestDto(List<String> validatorNameList) {
        this.validatorNameList = validatorNameList;
    }
}
