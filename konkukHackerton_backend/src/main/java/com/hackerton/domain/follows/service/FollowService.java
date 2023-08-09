package com.hackerton.domain.follows.service;

import com.hackerton.domain.follows.entity.Follows;
import com.hackerton.domain.follows.entity.FollowRepository;
import com.hackerton.domain.follows.exception.FollowMyselfFailException;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityAlreadyExistException;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public boolean follow(Long memberId, Long followMemberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        Member followMemember = memberRepository.findById(followMemberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + followMemberId));

        if(memberId == followMemberId)
            throw new FollowMyselfFailException(FOLLOW_MYSELF_FAIL, "해당 팔로우 ID는 본인입니다 : " + followMemberId);

        if(followRepository.existsByMemberIdAndFollowMemberId(memberId, followMemberId))
            throw new EntityAlreadyExistException(FOLLOW_ALREADY_EXIST, "해당 ID의 유저는 이미 팔로우 했습니다 : " + followMemberId);

        Follows follow = Follows.builder()
                .member(member)
                .followMember(followMemember)
                .build();
        followRepository.save(follow);

        /*
        알람 보내야됨
         */

        return true;
    }
}
