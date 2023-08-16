package com.hackerton.domain.challengeGroup.proof.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProofRepository extends JpaRepository<Proof, Long> {

    Optional<Proof> findByDateAndChallengeId(LocalDate date, Long challengeId);

    List<Proof> findAllByChallengeId(Long challengeId);
}

