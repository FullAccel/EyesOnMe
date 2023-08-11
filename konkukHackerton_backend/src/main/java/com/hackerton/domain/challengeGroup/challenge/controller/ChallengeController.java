package com.hackerton.domain.challengeGroup.challenge.controller;

import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    @PostMapping("/{memberId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long memberId) {

        return null;
    }

}
