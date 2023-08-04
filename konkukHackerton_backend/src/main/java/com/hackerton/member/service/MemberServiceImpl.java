package com.hackerton.member.service;

import com.hackerton.member.dto.MemberRepository;
import com.hackerton.member.domain.Member;
import com.hackerton.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public MemberResponseDto findMemberById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (!optionalMember.isPresent()) {
            throw new IllegalArgumentException("해당 멤버가 없습니다");
        }
        Member member = optionalMember.get();
        return getMemberResponseDto(member);
    }


    private static MemberResponseDto getMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .picture(member.getPicture())
                .build();
    }

    public boolean removeMember(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent())
            throw new IllegalArgumentException("해당 멤버가 없습니다.");
        memberRepository.deleteById(id);
        return true;
    }
}
