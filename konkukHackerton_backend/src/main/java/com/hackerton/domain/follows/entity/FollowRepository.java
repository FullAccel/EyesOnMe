package com.hackerton.domain.follows.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long> {

    public boolean existsByMemberIdAndFollowingMemberId(Long memberId, Long followingMemberId);

    Optional<Follows> findFollowsByMemberIdAndFollowingMemberId(Long memberId, Long followingMemberId);

    List<Follows> findAllByFollowingMemberId(Long followingMemberId);
}
