package com.hackerton.domain.challengeGroup.proofImage.dto;

import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProofImageResponseDto {

    private Long id;
    private String originName;
    private String storedName;
    private String accessUrl;

    public ProofImageResponseDto(ProofImage proofImage) {
        this.id = proofImage.getId();
        this.originName = proofImage.getOriginName();
        this.storedName = proofImage.getStoredName();
        this.accessUrl = proofImage.getAccessUrl();
    }
}
