package com.hackerton.domain.follows.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.follows.entity.Follows;
import com.hackerton.domain.follows.entity.FollowRepository;
import com.hackerton.domain.follows.exception.FollowMyselfFailException;
import com.hackerton.domain.member.dto.MemberFollowResponseDto;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityAlreadyExistException;
import com.hackerton.global.error.exception.EntityNotFoundException;
import com.hackerton.global.firebase.FCMService;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final FCMService fcmService;

    @Transactional
    public boolean sendFollowRequest (Long memberId, Long followingMemberId) throws FirebaseMessagingException {
        Member member = findMemberById(memberId);
        Member followingMember = findMemberById(followingMemberId);

        if(memberId == followingMemberId)
            throw new FollowMyselfFailException(FOLLOW_MYSELF_FAIL, "해당 팔로우 ID는 본인입니다 : " + followingMemberId);

//        if(followRepository.existsByMemberIdAndFollowingMemberId(memberId, followingMemberId))
//            throw new EntityAlreadyExistException(FOLLOW_ALREADY_EXIST, "해당 ID의 유저는 이미 팔로우 했습니다 : " + followingMemberId);

        //알람 보냄
        FCMNotificationRequestDto messageDto = FCMNotificationRequestDto.builder()
                .targetMemberId(followingMemberId)
                .title("팔로우 알림")
                .body(member.getName() + "님이 당신을 팔로우 했습니다")
                .build();

        fcmService.sendNotificationMessageByToken(messageDto);

        Follows follow = Follows.builder()
                .member(member)
                .followingMember(followingMember)
                .build();
        followRepository.save(follow);

        return true;
    }

    @Transactional
    public boolean unfollow(Long memberId, Long followingMemberId) {
        Member member = findMemberById(memberId);

        Member followingMember = findMemberById(followingMemberId);

        if(memberId == followingMemberId)
            throw new FollowMyselfFailException(UNFOLLOW_MYSELF_FAIL, "해당 언팔로우 ID는 본인입나다 : " + followingMemberId);

        Follows follows = followRepository.findFollowsByMemberIdAndFollowingMemberId(memberId, followingMemberId)
                .orElseThrow(() -> new EntityNotFoundException(UNFOLLOW_FAIL, "해당 Id( " + memberId + " ) 유저는 Id( " + followingMemberId + " ) 유저와 팔로우 되어있지 않습니다 "));

        followRepository.delete(follows);

        return true;
    }

    public List<MemberFollowResponseDto> getFollowingList(Long memberId) {
        Member member = findMemberById(memberId);
        if(member.getFollowings().size() == 0)
            throw new EntityNotFoundException(FOLLOWING_NOT_FOUND, "해당 Id의 유저는 팔로잉한 사람이 없습니다 : " + memberId);

        return member.getFollowings().stream()
                .map(MemberFollowResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<MemberFollowResponseDto> getFollowerList(Long memberId) {
        Member member = findMemberById(memberId);

        List<Follows> followerList = followRepository.findAllByFollowingMemberId(memberId);

        if(followerList.size() == 0)
            throw new EntityNotFoundException(FOLLOWER_NOT_FOUND, "해당 Id의 유저는 팔로워가 없습니다 : " + memberId);

        return followerList.stream()
                .map(entity -> new MemberFollowResponseDto(entity.getMember()))
                .collect(Collectors.toList());
    }


    private Member findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));
        return member;
    }


}
