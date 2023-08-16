package com.hackerton.domain.challengeGroup.validation.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeByValidatorResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeResponseDto;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeRepository;
import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.challengeGroup.validation.entity.ValidationRepository;
import com.hackerton.domain.challengeGroup.validation.exception.ValidatorNumOverFlowException;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityNotFoundException;
import com.hackerton.global.firebase.FCMService;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import com.hackerton.global.status.ProgressStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationRepository validationRepository;
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;
    private final FCMService fcmService;
    private final int MAX_VALIDATOR_NUM = 3;

    public List<ChallengeByValidatorResponseDto> getChallengeList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        List<ChallengeByValidatorResponseDto> challengeByValidatorResponseDtos = validationRepository.findAllValidationByValidatorMemberId(memberId)
                .stream().map(entity -> ChallengeByValidatorResponseDto.builder()
                        .entity(entity.getChallenge())
                        .build()
                )
                .collect(Collectors.toList());

        if(challengeByValidatorResponseDtos.isEmpty())
            throw new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 멤버에는 등록된 챌린지가 없습니다");

        return challengeByValidatorResponseDtos;
    }

    @Transactional
    public boolean entryChallenge(Long challengeId, Long validatorId) throws FirebaseMessagingException {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않거나 삭제 되어 검증자가 입장할 수 없습니다 : " + challengeId));

        Member validator = memberRepository.findById(validatorId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + validatorId));

        if(challenge.getValidations().size() > MAX_VALIDATOR_NUM)
            throw new ValidatorNumOverFlowException(VALIDATOR_UPPER_MAX_NUM);
        Validation validation = saveValidationByValidator(challenge ,validator);

        //challengeHost에게 메세지
        Member challengeHost = challenge.getMember();
        FCMNotificationRequestDto messageDto = FCMNotificationRequestDto.builder()
                .targetMemberId(challengeHost.getId())
                .title(validator + "님 챌린지 입장!")
                .body(validator.getName() + "님이 이제 " + challengeHost.getName() + "의 챌린지 인증을 시작합니다")
                .build();

        fcmService.sendNotificationMessageByToken(messageDto);
        return true;
    }

    @Transactional
    public Validation saveValidationByValidator(Challenge challenge, Member validator) {

        Validation validation = Validation.builder()
                .challenge(challenge)
                .validatorName(validator.getName())
                .creatorMember(challenge.getMember())
                .validatorMember(validator)
                .build();

        return validationRepository.save(validation);
    }

    @Transactional
    public boolean deleteValidation(Long challengeId, Long validatorId) {

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않지 않습니다 : " + challengeId));

        Member validator = memberRepository.findById(validatorId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 검증자가 존재하지 않습니다 : " + validatorId));

        validationRepository.deleteByChallengeIdAndValidatorMemberId(challengeId,validatorId);

        return true;
    }

    @Transactional
    public boolean checkValidationComplete(Long challengeId, Long validatorId) throws FirebaseMessagingException{

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않지 않습니다 : " + challengeId));

        Member validator = memberRepository.findById(validatorId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 검증자가 존재하지 않습니다 : " + validatorId));

        Validation validation = validationRepository.findValidationByChallengeIdAndValidatorMemberId(challengeId, validatorId).get();
        validation.setValidationStatus(ProgressStatus.COMPLETE);

        Boolean isFinishedValidation = true;
        int completeCount = 0;
        int incompleteCount = 0;
        
        List<Validation> validations = challenge.getValidations();
        
        for (Validation v : validations) {
            if (v.getValidationStatus() == ProgressStatus.COMPLETE) {
                completeCount++;
            } else if (v.getValidationStatus() == ProgressStatus.FAIL) {
                incompleteCount++;
            } else {
                isFinishedValidation = false;
                break;
            }
        }

        //아직 검증이 다 끝나지 않았으므로 다음 사람이 할 때까지 기다림
        if(!isFinishedValidation)
            return true;

        determiningChallengeComplete(challenge, completeCount, incompleteCount, validations);

        //검증 완료 되었으면 challegeHost한테 푸쉬알림 까지 보내고 종료
        sendValidationCompleteMessageToHost(challenge);

        return true;

    }

    private void sendValidationCompleteMessageToHost(Challenge challenge) throws FirebaseMessagingException {
        FCMNotificationRequestDto notificationRequestDto = FCMNotificationRequestDto.builder()
                .title("챌린지 검증 완료!")
                .body("챌린지 검증이 " + challenge.getChallengeStatus().getStatus() + "로 끝났습니다")
                .targetMemberId(challenge.getMember().getId())
                .build();

        fcmService.sendNotificationMessageByToken(notificationRequestDto);
    }
    private static void determiningChallengeComplete(Challenge challenge, int completeCount, int incompleteCount, List<Validation> validations) {
        int validatorNum = validations.size();
        switch (validatorNum) {
            case 1:
                //validations의 size = 1이므로 첫번째가 유일
                challenge.setChallengeStatus(validations.get(0).getValidationStatus());
                break;
            case 2:
                if (completeCount >= 1) {
                    challenge.setChallengeStatus(ProgressStatus.COMPLETE);
                } else {
                    challenge.setChallengeStatus(ProgressStatus.FAIL);
                }
                break;
            case 3:
                if (completeCount >= 2) {
                    challenge.setChallengeStatus(ProgressStatus.COMPLETE);
                } else if (incompleteCount >= 2) {
                    challenge.setChallengeStatus(ProgressStatus.FAIL);
                } 
                break;
        }
    }

    @Transactional
    public boolean checkValidationFail(Long challengeId, Long validatorId) throws FirebaseMessagingException{

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 챌린지가 존재하지 않지 않습니다 : " + challengeId));

        Member validator = memberRepository.findById(validatorId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 검증자가 존재하지 않습니다 : " + validatorId));

        Validation validation = validationRepository.findValidationByChallengeIdAndValidatorMemberId(challengeId, validatorId).get();
        validation.setValidationStatus(ProgressStatus.FAIL);

        Boolean isFinishedValidation = true;
        int completeCount = 0;
        int incompleteCount = 0;

        List<Validation> validations = challenge.getValidations();

        for (Validation v : validations) {
            if (v.getValidationStatus() == ProgressStatus.COMPLETE) {
                completeCount++;
            } else if (v.getValidationStatus() == ProgressStatus.FAIL) {
                incompleteCount++;
            } else {
                isFinishedValidation = false;
                break;
            }
        }

        //아직 검증이 다 끝나지 않았으므로 다음 사람이 할 때까지 기다림
        if(!isFinishedValidation)
            return true;

        determiningChallengeComplete(challenge, completeCount, incompleteCount, validations);

        //검증 완료 되었으면 challegeHost한테 푸쉬알림 까지 보내고 종료
        sendValidationCompleteMessageToHost(challenge);

        return true;

    }

}
