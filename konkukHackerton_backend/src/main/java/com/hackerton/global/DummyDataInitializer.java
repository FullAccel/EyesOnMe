package com.hackerton.global;

import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.category.entity.CategoryCode;
import com.hackerton.domain.category.entity.CategoryRepository;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeRequestDto;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.challenge.entity.ChallengeRepository;
import com.hackerton.domain.challengeGroup.challenge.entity.ValidationInterval;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.domain.challengeGroup.proof.entity.ProofRepository;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImage;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImageRepository;
import com.hackerton.domain.challengeGroup.proofImage.service.ProofImageService;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer {

    private final MemberRepository memberRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final ToDoRepository toDoRepository;
    private final CategoryRepository categoryRepository;
    private final ChallengeRepository challengeRepository;
    private final ProofRepository proofRepository;
    private final ProofImageRepository proofImageRepository;

    @PostConstruct
    public void init() {
        Member member1 = Member.builder()
                .name("박세준")
                .email("psj6570@naver.com")
                .profileUrl("https://k.kakaocdn.net/dn/NGzCh/btsl4lPk79L/knD22zGUTndKocBoTbKvx1/img_640x640.jpg")
                .planSuccessCount(0)
                .challengeSuccessCount(0)
                .firebaseToken("caZJbB4hS1uirQ0HiYaKvB:APA91bFWcND0BTRoDudm4r_TMZxu0WSZTajSdvrfn2fSc4wOjQ5mkzrvd8kMVYb7L65lbCHE8MutNrTfHwLKT4TsPRK_Stdh7iWhDW80p3hF_DmEGarzAx7f8B9j_sMFv1tjnpLjCQsd")
                .build();
        Member savedMember1 = memberRepository.save(member1);
        Member member2 = Member.builder()
                .name("은*")
                .email("ksytpdus0304@gmail.com")
                .profileUrl("https://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg")
                .planSuccessCount(0)
                .challengeSuccessCount(0)
                .firebaseToken("c5HynNSxStSNR8AcTwgl1A:APA91bEqS2NL_9EWXK-RPliC7TCk7cv1Q86RcSVvO7yCXmouMf4-0cTE5nCsFnryS7CpgQj0BnnWIYo2Ogx-1yaYSK_4x6DQdS2WYas3rc55Y-3BYkRrGuRkXHh5fqfr6ttNez6SlwQe")
                .build();
        Member savedMember2 = memberRepository.save(member2);

        DailyPlan dailyPlan = DailyPlan.builder()
                .member(savedMember1)
                .yearMonth("202308")
                .day(05)
                .build();
        DailyPlan savedDailyPlan = dailyPlanRepository.save(dailyPlan);

        Category category = Category.builder()
                .categoryCode(CategoryCode.DAILY)
                .countByToDo(1)
                .countByChallenge(0)
                .successToDoCount(0)
                .successChallengeCount(0)
                .member(member2)
                .build();

        Category savedCategory = categoryRepository.save(category);

        ToDo todo = ToDo.builder()
                .title("title")
                .alarmStartTime("08:30")
                .alarmEndTime("23:30")
                .dailyPlan(savedDailyPlan)
                .category(savedCategory)
                .build();

        toDoRepository.save(todo);

        Challenge challenge = Challenge.builder()
                .title("운동하기")
                .deadline(LocalDate.of(2023,8,31))
                .totalProofNum(2)
                .currentSuccessNum(2)
                .validationInterval(ValidationInterval.findByInterval("VI07"))
                .validationCountPerInterval(3)
                .build();

        challenge.setCategory(savedCategory);
        challenge.setMember(member2);

        Challenge savedChallenge = challengeRepository.save(challenge);

        Proof proof1 = Proof.builder()
                .date(LocalDate.of(2023, 8, 17))
                .writing("오늘 운동 개 힘들었삼. 근육통 지렸다리 오졌다리")
                .build();

        Proof savedProof1 = proofRepository.save(proof1);

        ProofImage proofImage1 = ProofImage.builder()
                .originName("MA_00031467_hrit6x.webp")
                .storedName("pngtree-coil-spring-icon-helical-energy-png-image_4668025.jpeg")
                .accessUrl("https://hackerton.s3.ap-northeast-2.amazonaws.com/b551c22c-604c-41fe-b370-b39ac7589bae..jpeg")
                .build();
        proofImage1.setProof(savedProof1);

        proofImageRepository.save(proofImage1);

        proof1.setChallenge(savedChallenge);
        savedProof1 = proofRepository.save(proof1);

        Proof proof2 = Proof.builder()
                .date(LocalDate.of(2023, 8, 16))
                .writing("오늘 운동 개 힘들었삼. 근육통 지렸다리 오졌다리")
                .build();

        Proof savedProof2 = proofRepository.save(proof2);

        ProofImage proofImage2 = ProofImage.builder()
                .originName("MA_00031467_hrit6x.webp")
                .storedName("pngtree-coil-spring-icon-helical-energy-png-image_4668025.jpeg")
                .accessUrl("https://hackerton.s3.ap-northeast-2.amazonaws.com/b551c22c-604c-41fe-b370-b39ac7589bae..jpeg")
                .build();
        proofImage2.setProof(savedProof2);

        proofImageRepository.save(proofImage2);

        proof2.setChallenge(savedChallenge);
        savedProof2 = proofRepository.save(proof2);



    }
}
