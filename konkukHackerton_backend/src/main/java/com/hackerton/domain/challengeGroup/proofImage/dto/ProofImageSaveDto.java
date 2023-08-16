package com.hackerton.domain.challengeGroup.proofImage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProofImageSaveDto {

    private List<MultipartFile> images = new ArrayList<>();
}
