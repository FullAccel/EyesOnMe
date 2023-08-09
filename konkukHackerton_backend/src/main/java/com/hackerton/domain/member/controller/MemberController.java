package com.hackerton.domain.member.controller;

import com.hackerton.domain.member.dto.MemberRequestDto;
import com.hackerton.domain.member.dto.MemberResponseDto;
import com.hackerton.domain.member.service.MemberService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> saveOrUpdateMember(@RequestBody MemberRequestDto memberRequestDto)
    {
        MemberResponseDto memberResponseDto = memberService.saveOrUpdateMember(memberRequestDto);
        return ResponseEntity.ok(ResultResponse.of(MEMBER_SAVE_OR_UPDATE_SUCCESS, memberResponseDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultResponse> getMember(@PathVariable Long id) {
        MemberResponseDto memberById = memberService.findMemberById(id);
        return ResponseEntity.ok(ResultResponse.of(GET_USERPROFILE_SUCCESS, memberById));
    }

    @GetMapping("/follow/{email}")
    public ResponseEntity<ResultResponse> getMemberByEmail(@PathVariable String email) {
        MemberResponseDto memberByEmail = memberService.findMemberByEmail(email);
        return ResponseEntity.ok(ResultResponse.of(GET_USERPROFILE_SUCCESS, memberByEmail));
    }
}
