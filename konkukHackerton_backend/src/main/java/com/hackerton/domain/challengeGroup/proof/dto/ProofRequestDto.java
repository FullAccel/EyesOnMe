package com.hackerton.domain.challengeGroup.proof.dto;

import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProofRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String writing;
    private List<MultipartFile> images = new ArrayList<>();

    public Proof toEntity(){
        return Proof.builder()
                .date(this.date)
                .writing(this.writing)
                .build();
    }
}
