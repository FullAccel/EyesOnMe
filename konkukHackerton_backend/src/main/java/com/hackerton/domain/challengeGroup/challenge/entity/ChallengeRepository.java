package com.hackerton.domain.challengeGroup.challenge.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findAllByMemberId(Long memberId);
}
