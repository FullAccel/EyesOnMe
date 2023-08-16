package com.hackerton.domain.challengeGroup.proof.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.challengeGroup.proof.dto.ProofGetRequestDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofRequestDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofResponseDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofUpdateDto;
import com.hackerton.domain.challengeGroup.proof.service.ProofService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proof")
public class ProofController {

    private final ProofService proofService;

    @PostMapping("/{challengeId}")
    public ResponseEntity<ResultResponse> save(@PathVariable Long challengeId, @ModelAttribute ProofRequestDto proofRequestDto) throws IOException, FirebaseMessagingException {
        ProofResponseDto proofResponseDto = proofService.saveProof(challengeId, proofRequestDto);
        return ResponseEntity.ok(ResultResponse.of(PROOF_UPLOAD_SUCCESS, proofResponseDto));
    }

    @GetMapping("/{challengeId}")
    public ResponseEntity<ResultResponse> getProof(@PathVariable Long challengeId, @RequestBody ProofGetRequestDto proofGetRequestDto) {
        ProofResponseDto proofByDate = proofService.getProofByDate(challengeId, proofGetRequestDto);
        return ResponseEntity.ok(ResultResponse.of(GET_PROOF_SUCCESS, proofByDate));
    }

    @GetMapping("/{challengeId}/list")
    public ResponseEntity<ResultResponse> getProofList(@PathVariable Long challengeId) {
        List<ProofResponseDto> proofListByChallengeList = proofService.getProofListByChallenge(challengeId);
        return ResponseEntity.ok(ResultResponse.of(GET_PROOF_SUCCESS, proofListByChallengeList));
    }

    @PutMapping("/{challengeId}/{proofId}")
    public ResponseEntity<ResultResponse> updateProof(@PathVariable Long challengeId, @PathVariable Long proofId, @RequestBody ProofUpdateDto proofUpdateDto) {
        String updatedWriting = proofService.updateProof(challengeId, proofId, proofUpdateDto);
        return ResponseEntity.ok(ResultResponse.of(UPDATE_PROOF_SUCCESS, updatedWriting));
    }

    @DeleteMapping("/{proofId}")
    public ResponseEntity<ResultResponse> deleteProof(@PathVariable Long proofId)
    {
        boolean isDeleted = proofService.deleteProof(proofId);
        return ResponseEntity.ok(ResultResponse.of(DELETE_PROOF_SUCCESS, isDeleted));
    }
}
