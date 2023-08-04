package com.hackerton.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerton.security.dto.SessionMember;
import com.hackerton.member.service.MemberService;
import com.hackerton.member.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpSession httpSession;

    @GetMapping("/loginSuccess")
    public void loginSuccess(HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        SessionMember user = (SessionMember) httpSession.getAttribute("user");
        String result = "";
        if(user == null)
            throw new IllegalArgumentException();

        MemberResponseDto memberResponseDto = memberService.findMemberById(user.getId());
        result = objectMapper.writeValueAsString(memberResponseDto);
        PrintWriter writer = response.getWriter();
        writer.print(result);
        System.out.println(result);
//        response.setHeader("Location", "http://localhost:3000/oauth/callback/kakao");
//        response.setStatus ( HttpServletResponse.SC_MOVED_PERMANENTLY );
//        writer.flush();
    }

    @GetMapping("/login")
    public MemberResponseDto login(){
        SessionMember user = (SessionMember) httpSession.getAttribute("user");
        MemberResponseDto memberResponseDto = memberService.findMemberById(user.getId());

        return memberResponseDto;

    }
}
