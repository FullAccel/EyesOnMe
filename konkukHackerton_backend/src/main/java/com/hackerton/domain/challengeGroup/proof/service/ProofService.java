package com.hackerton.domain.challengeGroup.proof.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeRepository;
import com.hackerton.domain.challengeGroup.proof.dto.ProofGetRequestDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofRequestDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofResponseDto;
import com.hackerton.domain.challengeGroup.proof.dto.ProofUpdateDto;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.domain.challengeGroup.proof.entity.ProofRepository;
import com.hackerton.domain.challengeGroup.proofImage.dto.ProofImageResponseDto;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImage;
import com.hackerton.domain.challengeGroup.proofImage.service.ProofImageService;
import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityNotFoundException;
import com.hackerton.global.firebase.FCMService;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import com.hackerton.global.firebase.exception.FirebaseTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProofService {

    private final MemberRepository memberRepository;
    private final ProofRepository proofRepository;
    private final ChallengeRepository challengeRepository;
    private final ProofImageService proofImageService;
    private final FCMService fcmService;

    @Transactional
    public ProofResponseDto saveProof(Long challengeId, ProofRequestDto proofRequestDto) throws IOException  {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않거나 삭제 되어 검증자가 입장할 수 없습니다 : " + challengeId));

        Proof proof = proofRequestDto.toEntity();

        challenge.plusCurrentSuccessNum();
        proof.setChallenge(challenge);
        Proof tempProof = proofRepository.save(proof);

        List<ProofImage> proofImages = proofImageService.saveImageList(proofRequestDto.getImages(), proof);

        proof.setProofImages(proofImages);


        ProofResponseDto proofResponseDto = ProofResponseDto.builder()
                .entity(tempProof)
                .build();

        setImagesToProofResponseDto(proofImages, proofResponseDto);


        //검증자들에게 푸쉬 알람
        sendProofUploadMessage(challenge);

        return proofResponseDto;
    }

    private static void setImagesToProofResponseDto(List<ProofImage> proofImages, ProofResponseDto proofResponseDto) {
        List<ProofImageResponseDto> proofImageResponseDtos = proofImages.stream()
                .map(ProofImageResponseDto::new)
                .collect(Collectors.toList());
        proofResponseDto.setImages(proofImageResponseDtos);
    }

    private void sendProofUploadMessage(Challenge challenge) {
        challenge.getValidations().stream()
                        .forEach(entity -> {
                            try{
                                sendProofUploadMessageToValidator(entity);
                            } catch (FirebaseMessagingException e) {
                                throw new FirebaseTokenNotFoundException(TOKEN_NOT_FOUND,"해당 멤버의 토큰이 올바르지 않습니다 : " +entity.getValidatorMember().getId());
                            }
                                });
    }

    public void sendProofUploadMessageToValidator(Validation validation) throws FirebaseMessagingException {
        Member validatorMember = validation.getValidatorMember();
        if(validatorMember == null)
            return;

        FCMNotificationRequestDto requestDto = FCMNotificationRequestDto.builder()
                .targetMemberId(validatorMember.getId())
                .title("챌린지 인증 업로드")
                .body(validatorMember.getName() + "님의 챌린지 인증이 올라왔어요!")
                .build();

        fcmService.sendNotificationMessageByToken(requestDto);
    }

    public ProofResponseDto getProofByDate(Long challengeId,  ProofGetRequestDto proofGetRequestDto){

        Member member = memberRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + challengeId));

         return proofRepository.findByDateAndChallengeId(proofGetRequestDto.getDate(), challengeId)
                 .map(this::getProofResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(PROOF_NOT_FOUND, "해당 날짜의 Proof가 없습니다 : " + proofGetRequestDto.getDate()));
    }

    private ProofResponseDto getProofResponseDto(Proof entity) {
        ProofResponseDto proofResponseDto = ProofResponseDto.builder()
                .entity(entity)
                .build();
        List<ProofImageResponseDto> proofImageResponseDtos = entity.getProofImages().stream()
                .map(ProofImageResponseDto::new)
                .collect(Collectors.toList());
        proofResponseDto.setImages(proofImageResponseDtos);
        return proofResponseDto;
    }

    public List<ProofResponseDto> getProofListByChallenge(Long challengeId){
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않거나 삭제 되어 검증자가 입장할 수 없습니다 : " + challengeId));

        List<ProofResponseDto> proofResponseDtos = proofRepository.findAllByChallengeId(challengeId).stream()
                .map(this::getProofResponseDto)
                .collect(Collectors.toList());

        if(proofResponseDtos.size() == 0)
            throw new EntityNotFoundException(PROOF_NOT_FOUND, "해당 챌린지에는 아직 proof가 등록되지 않았습니다");

        return proofResponseDtos;
    }

    @Transactional
    public String updateProof(Long challengeId, Long proofId, ProofUpdateDto proofUpdateDto){
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않거나 삭제 되어 검증자가 입장할 수 없습니다 : " + challengeId));

        Proof proof = proofRepository.findById(proofId)
                .map(entity -> entity.update(proofUpdateDto.getWriting()))
                .orElseThrow(() -> new EntityNotFoundException(PROOF_NOT_FOUND, "해당 Id의 Proof가 없습니다 : " + proofId));

        return proof.getWriting();
    }

    @Transactional
    public boolean deleteProof(Long proofId) {
        Proof proof = proofRepository.findById(proofId)
                .orElseThrow(() -> new EntityNotFoundException(PROOF_NOT_FOUND, "해당 Id의 Proof가 없습니다 : " + proofId));
        proof.getChallenge().minusCurrentSuccessNum();
        proofRepository.deleteById(proofId);
        return true;
    }


}
