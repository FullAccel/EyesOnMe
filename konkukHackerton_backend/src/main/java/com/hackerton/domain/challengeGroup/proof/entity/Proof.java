package com.hackerton.domain.challengeGroup.proof.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.check.entity.Check;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Proof extends BaseTimeEntity {

    @Id
    @Column(name = "PROOF_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    private String writing;
    private String proofShot;

    @ManyToOne
    @JoinColumn(name = "CHECK_ID")
    private Check check;


}
