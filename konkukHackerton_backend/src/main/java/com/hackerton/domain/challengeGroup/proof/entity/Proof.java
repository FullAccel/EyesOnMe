package com.hackerton.domain.challengeGroup.proof.entity;

import com.hackerton.domain.BaseTimeEntity;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImage;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Proof extends BaseTimeEntity {

    @Id
    @Column(name = "PROOF_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate date;

    private String writing;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @OneToMany(mappedBy = "proof", cascade = CascadeType.REMOVE)
    private List<ProofImage> proofImages;

    @Builder
    public Proof(LocalDate date, String writing) {
        this.date = date;
        this.writing = writing;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setProofImages(List<ProofImage> proofImages) {
        this.proofImages = proofImages;
    }

    public Proof update(String updateWriting) {
        this.writing = updateWriting;
        return this;
    }
}
