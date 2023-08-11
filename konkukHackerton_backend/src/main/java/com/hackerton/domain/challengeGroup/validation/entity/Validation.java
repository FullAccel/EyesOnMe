package com.hackerton.domain.challengeGroup.validation.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.check.entity.Check;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Validation extends BaseTimeEntity {

    @Id
    @Column(name = "VALIDATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String validatorName;

    @OneToMany(mappedBy = "validation")
    private List<Check> checks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @Builder
    public Validation(String validatorName, Challenge challenge) {
        this.validatorName = validatorName;
        this.challenge = challenge;
    }
}
