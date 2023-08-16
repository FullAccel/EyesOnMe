package com.hackerton.domain.challengeGroup.challenge.service;

import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.category.service.CategoryService;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeRequestDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeUpdateResponseDto;
import com.hackerton.domain.challengeGroup.challenge.dto.ValidatorRequestDto;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeRepository;
import com.hackerton.domain.challengeGroup.challenge.exception.ValidationCountOverflowException;
import com.hackerton.domain.challengeGroup.challenge.exception.ValidationIntervalTooSmall;
import com.hackerton.domain.challengeGroup.challenge.exception.ValidatorNumberException;
import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.challengeGroup.validation.entity.ValidationRepository;
import com.hackerton.domain.challengeGroup.validation.service.ValidationService;
import com.hackerton.domain.member.dto.MemberValidatorResponseDto;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;
    private final CategoryService categoryService;
    private final ValidationService validationService;
    private final ValidationRepository validationRepository;
    private final int MAX_VALIDATOR_NUMBER = 3;
    private final int MIN_COUNT_PER_INTERVAL = 0;
    private final int MAX_COUNT_PER_INTERVAL = 99;

    @Transactional
    public Long save(Long memberId, ChallengeRequestDto challengeRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        Challenge challenge = challengeRequestDto.toEntity();
        int validationCountPerInterval = challenge.getValidationCountPerInterval();
        if(validationCountPerInterval <= MIN_COUNT_PER_INTERVAL || validationCountPerInterval > MAX_COUNT_PER_INTERVAL)
            throw new ValidationCountOverflowException(VALIDATION_COUNT_EXCEPTION, "입력된 검증횟수 : " + validationCountPerInterval);

        if(!challenge.isTotalDaysBiggerThanValidationInterval())
            throw new ValidationIntervalTooSmall(VALIDATION_INTERVAL_TOO_SMALL);
        challenge.setTotalProofNum();
        Category category = categoryService.save(challengeRequestDto.getCategoryCode(), challenge, member);

        challenge.setMember(member);
        challenge.setCategory(category);

        return challengeRepository.save(challenge).getId();
    }

//    @Transactional
//    public boolean addValidator(Long challengeId, ValidatorRequestDto validatorRequestDto) {
//        Challenge challenge = challengeRepository.findById(challengeId)
//                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 Id의 챌린지가 존재 하지 않습니다 : " + challengeId));
//
//        List<String> validatorNameList = validatorRequestDto.getValidatorNameList();
//        if(challenge.getValidations().size() + validatorNameList.size() > MAX_VALIDATOR_NUMBER)
//            throw new ValidatorNumberException(VALIDATOR_UPPER_MAX_NUM);
//
////        if(validatorNameList.size() == 0)
////            throw new ValidatorNumberException(VALIDATOR_UNDER_ZERO);
//        validatorNameList.forEach(name -> validationService.save(name, challenge));
//
//        return true;
//    }

    public ChallengeResponseDto getChallenge(Long challengeId) {
        ChallengeResponseDto challengeResponseDto = challengeRepository.findById(challengeId)
                .map(ChallengeResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 Id의 챌린지가 존재 하지 않습니다 : " + challengeId));

        List<MemberValidatorResponseDto> validators = getValidatorNames(challengeId);

        challengeResponseDto.setValidatorList(validators);

        return challengeResponseDto;
    }

    private List<MemberValidatorResponseDto> getValidatorNames(Long challengeId) {
        return validationRepository.findAllValidationByChallengeId(challengeId).stream()
                .map(MemberValidatorResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ChallengeResponseDto> getChallengeList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));

        List<ChallengeResponseDto> challengeResponseDtos = challengeRepository.findAllByMemberId(memberId).stream()
                .map(ChallengeResponseDto::new)
                .map(entity -> entity.setValidatorList(getValidatorNames(entity.getId())))
                .collect(Collectors.toList());

        if(challengeResponseDtos.isEmpty())
            throw new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 멤버에는 등록된 챌린지가 없습니다");

        return challengeResponseDtos;
    }

    public ChallengeUpdateResponseDto updateChallenge(Long challengeId, ChallengeRequestDto challengeRequestDto) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .map(entity -> entity.update(challengeRequestDto))
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_NOT_FOUND, "해당 Id의 챌린지가 존재 하지 않습니다 : " + challengeId));

        Category update = categoryService.update(challenge.getCategory(), challengeRequestDto.getCategoryCode(), challenge);
        challenge.setCategory(update);

        /*
        검증자 업데이트는 따로 메서드 만들기
         */
        return ChallengeUpdateResponseDto.builder()
                .entity(challenge)
                .build();
    }
}
