package com.hackerton.domain.challengeGroup.proof.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.domain.challengeGroup.proofImage.dto.ProofImageResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProofResponseDto {

    private Long proofId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate date;
    private String writing;
    private List<ProofImageResponseDto> images = new ArrayList<>();

    @Builder
    public ProofResponseDto(Proof entity) {
        this.proofId = entity.getId();
        this.date = entity.getDate();
        this.writing = entity.getWriting();
    }

    public void setImages(List<ProofImageResponseDto> images) {
        this.images = images;
    }



}
