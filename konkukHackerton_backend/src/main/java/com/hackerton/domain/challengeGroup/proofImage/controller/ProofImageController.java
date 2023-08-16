package com.hackerton.domain.challengeGroup.proofImage.controller;

import com.hackerton.domain.challengeGroup.proofImage.dto.ProofImageSaveDto;
import com.hackerton.domain.challengeGroup.proofImage.service.ProofImageService;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.hackerton.global.result.ResultCode.*;


@RestController
@RequiredArgsConstructor
public class ProofImageController {

    private final ProofImageService imageService;

//    @PostMapping(value = "/image")
//    public ResponseEntity<ResultResponse> saveImage(@ModelAttribute ProofImageSaveDto proofImageSaveDto) throws IOException {
//        List<String> imageAccesUrlList = imageService.saveImageList(proofImageSaveDto);
//        return ResponseEntity.ok(ResultResponse.of(PROOF_IMAGE_UPLOAD_SUCCESS, imageAccesUrlList));
//    }
}
