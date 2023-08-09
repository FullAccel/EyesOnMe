package com.hackerton.domain.member.service;

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
        return memberRepository.save(member).toMemberResponseDto();
    }

    public MemberResponseDto findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND,"해당 이메일의 유저가 없습니다 : " + email)).toMemberResponseDto();
    }

    public MemberResponseDto findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 id의 유저가 없습니다 : " + id)).toMemberResponseDto();
    }
}
