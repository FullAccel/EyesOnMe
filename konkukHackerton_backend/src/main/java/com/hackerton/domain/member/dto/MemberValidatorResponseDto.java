package com.hackerton.domain.member.dto;

import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Getter
@NoArgsConstructor
public class MemberValidatorResponseDto {
    private Long id;
    private String name;

    public MemberValidatorResponseDto(Validation entity) {
        this.id = entity.getValidatorMember().getId();
        this.name = entity.getValidatorMember().getName();
    }
}
