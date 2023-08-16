package com.hackerton.domain.challengeGroup.challenge.controller;

import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeRequestDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeUpdateResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ValidatorRequestDto;
import com.hackerton.domain.challengeGroup.challenge.service.ChallengeService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/{memberId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long memberId, @RequestBody ChallengeRequestDto challengeRequestDto) {
        Long saveId = challengeService.save(memberId, challengeRequestDto);
        return ResponseEntity.ok(ResultResponse.of(SAVE_CHALLENGE_SUCCESS,saveId));
    }

//    @PostMapping("/{challengeId}/validator")
//    public ResponseEntity<ResultResponse> addValidator(@PathVariable Long challengeId, @RequestBody ValidatorRequestDto validatorRequestDto) {
//        boolean isAddValidator = challengeService.addValidator(challengeId, validatorRequestDto);
//        return ResponseEntity.ok(ResultResponse.of(SAVE_VALIDATOR_SUCCESS,isAddValidator));
//    }

    @GetMapping("/{challengeId}")
    public ResponseEntity<ResultResponse> getChallenge(@PathVariable Long challengeId) {
        ChallengeResponseDto responseDto = challengeService.getChallenge(challengeId);
        return ResponseEntity.ok(ResultResponse.of(GET_CHALLENGE_SUCCESS, responseDto));
    }

    @GetMapping("/{memberId}/list")
    public ResponseEntity<ResultResponse> getChallengeList(@PathVariable Long memberId) {
        List<ChallengeResponseDto> challengeList = challengeService.getChallengeList(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_ALL_CHALLENGE_SUCCESS, challengeList));
    }

    @PutMapping("/{challengeId}")
    public ResponseEntity<ResultResponse> updateChallenge(@PathVariable Long challengeId, @RequestBody ChallengeRequestDto challengeRequestDto) {
        ChallengeUpdateResponseDto challengeUpdateResponseDto = challengeService.updateChallenge(challengeId, challengeRequestDto);
        return ResponseEntity.ok(ResultResponse.of(UPDATE_CHALLENGE_SUCCESS, challengeUpdateResponseDto));

    }
}
