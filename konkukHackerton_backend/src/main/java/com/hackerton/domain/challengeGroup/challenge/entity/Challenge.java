package com.hackerton.domain.challengeGroup.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.challengeGroup.challenge.dto.ChallengeRequestDto;
import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.global.status.ProgressStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Challenge {

    @Id
    @Column(name = "CHALLENGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate deadline;

    private int totalProofNum;
    private int currentSuccessNum;

    @Enumerated(EnumType.STRING)
    private ProgressStatus challengeStatus;


    @Enumerated(EnumType.STRING)
    private ValidationInterval validationInterval;

    private int validationCountPerInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "challenge")
    private List<Proof> proofs = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<Validation> validations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public Challenge(String title,
                     Member member,
                     int totalProofNum,
                     int currentSuccessNum,
                     LocalDate deadline,
                     ValidationInterval validationInterval,
                     int validationCountPerInterval) {

        this.title = title;
        this.member = member;
        this.totalProofNum = totalProofNum;
        this.currentSuccessNum = currentSuccessNum;
        this.deadline = deadline;
        this.challengeStatus = ProgressStatus.IN_PROGRESS;
        this.validationInterval = validationInterval;
        this.validationCountPerInterval = validationCountPerInterval;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Challenge update(ChallengeRequestDto challengeRequestDto) {
        this.title = challengeRequestDto.getTitle();
        this.deadline = challengeRequestDto.getDeadline();
        this.validationInterval = ValidationInterval.findByInterval(challengeRequestDto.getValidationIntervalCode());
        this.validationCountPerInterval = challengeRequestDto.getValidationCountPerInterval();

        return this;
    }

    public int calculateDDAY() {
        return Period.between(LocalDate.now(), this.deadline).getDays();
    }


    public void setTotalProofNum() {
        int totalDays = Period.between(LocalDate.now(), this.deadline).getDays();
        this.totalProofNum = (int) (Math.round( totalDays / (double)validationInterval.getDayCount()) * validationCountPerInterval);
    }

    public boolean isTotalDaysBiggerThanValidationInterval()
    {
        int totalDays = Period.between(LocalDate.now(), this.deadline).getDays();
        return totalDays > validationInterval.getDayCount();
    }

    public void plusCurrentSuccessNum() {
        this.currentSuccessNum += 1;
    }

    public void minusCurrentSuccessNum() {
        this.currentSuccessNum -= 1;
    }

    public double getAchievementRate(){
        return (int)((currentSuccessNum/(double)totalProofNum)*100);
    }

    public void setChallengeStatus(ProgressStatus challengeStatus) {
        this.challengeStatus = challengeStatus;
    }
}
