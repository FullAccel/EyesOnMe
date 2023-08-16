package com.hackerton.domain.member.service;

import com.hackerton.domain.member.dto.MemberFirebaseTokenRequestDto;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.dto.MemberRequestDto;
import com.hackerton.domain.member.dto.MemberResponseDto;
import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveOrUpdateMember(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail())
                .map(entity -> entity.update(memberRequestDto.getName(), memberRequestDto.getProfileUrl()))
                .orElse(memberRequestDto.toEntity());

        return MemberResponseDto.builder()
                .entity(memberRepository.save(member))
                .build();
    }

    public MemberResponseDto findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND,"해당 이메일의 유저가 없습니다 : " + email));
    }

    public MemberResponseDto findMemberById(Long id) {
        return memberRepository.findById(id)
                .map(MemberResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 id의 유저가 없습니다 : " + id));
    }

    @Transactional
    public boolean saveOrUpdateFireBaseTokenByMemberId(Long memberId, MemberFirebaseTokenRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));
        member.setFirebaseToken(requestDto.getFirebaseToken());
        return true;
    }
}
