package com.hackerton.domain.challengeGroup.validation.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeByValidatorResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeResponseDto;
import com.hackerton.domain.challengeGroup.validation.service.ValidationService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validation")
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping("/entry/{challengeId}/{memberId}")
    public ResponseEntity<ResultResponse> entryChallenge(@PathVariable Long challengeId, @PathVariable Long memberId) throws FirebaseMessagingException {
        boolean isEntrySuccess = validationService.entryChallenge(challengeId, memberId);
        return ResponseEntity.ok(ResultResponse.of(VALIDATOR_ENTRY_SUCCESS, isEntrySuccess));
    }

    @DeleteMapping("/{challengeId}/{validatorId}")
    public ResponseEntity<ResultResponse> deleteValidation(@PathVariable Long challengeId, @PathVariable Long validatorId) {
        boolean isDeleted = validationService.deleteValidation(challengeId, validatorId);
        return ResponseEntity.ok(ResultResponse.of(DELETE_VALIDATION_SUCCESS, isDeleted));
    }

    @PostMapping("/check/complete/{challengeId}/{validatorId}")
    public ResponseEntity<ResultResponse> checkValidationComplete(@PathVariable Long challengeId, @PathVariable Long validatorId) throws FirebaseMessagingException{
        boolean isCheck = validationService.checkValidationComplete(challengeId, validatorId);
        return ResponseEntity.ok(ResultResponse.of(CHECK_VALIDATION_SUCCESS, isCheck));
    }

    @PostMapping("/check/fail/{challengeId}/{validatorId}")
    public ResponseEntity<ResultResponse> checkValidationFail(@PathVariable Long challengeId, @PathVariable Long validatorId) throws FirebaseMessagingException{
        boolean isCheck = validationService.checkValidationFail(challengeId, validatorId);
        return ResponseEntity.ok(ResultResponse.of(CHECK_VALIDATION_SUCCESS, isCheck));
    }

    @GetMapping("/validation/challengeList/{memberId}")
    public ResponseEntity<ResultResponse> getChallengeListFromValidator(@PathVariable Long memberId) {
        List<ChallengeByValidatorResponseDto> challengeList = validationService.getChallengeList(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_ALL_CHALLENGE_SUCCESS, challengeList));

    }


}
