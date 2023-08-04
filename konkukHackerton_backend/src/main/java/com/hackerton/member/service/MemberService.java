package com.hackerton.member.service;

import com.hackerton.member.dto.MemberResponseDto;

public interface MemberService {

    public MemberResponseDto findMemberById(Long id);

    public boolean removeMember(Long id);
}
