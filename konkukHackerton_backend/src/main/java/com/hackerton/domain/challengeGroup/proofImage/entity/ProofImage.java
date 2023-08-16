package com.hackerton.domain.challengeGroup.proofImage.entity;

import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class ProofImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proof_image_id")
    private Long id;

    private String originName;
    private String storedName;
    private String accessUrl;

    @ManyToOne
    @JoinColumn(name = "PROOF_ID")
    private Proof proof;

    @Builder
    public ProofImage(String originName, String storedName, String accessUrl) {
        this.originName = originName;
        this.storedName = getFileName(originName);
        this.accessUrl = accessUrl;
    }

    public String extractExtension(String originName) {
        int index = originName.lastIndexOf('.');
        return originName.substring(index, originName.length());
    }

    public String getFileName(String originName) {
        return UUID.randomUUID() + "." + extractExtension(originName);
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }
}
