package com.hackerton.domain.challengeGroup.validation.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {

    Optional<Validation> findValidationByChallengeIdAndValidatorMemberId(Long challengeId, Long validatorId);

    List<Validation> findAllValidationByChallengeId(Long challengeId);

    List<Validation> findAllValidationByValidatorMemberId(Long validatorId);


    void deleteByChallengeIdAndValidatorMemberId(Long challengeId, Long validatorMemberId);
}
