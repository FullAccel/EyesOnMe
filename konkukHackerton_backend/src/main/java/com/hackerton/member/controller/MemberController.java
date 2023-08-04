package com.hackerton.member.controller;

import com.hackerton.member.service.MemberService;
import com.hackerton.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("{id}")
    public MemberResponseDto getMember(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }
}
