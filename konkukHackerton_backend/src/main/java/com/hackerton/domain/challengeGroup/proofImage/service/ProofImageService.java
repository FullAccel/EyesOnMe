package com.hackerton.domain.challengeGroup.proofImage.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImage;
import com.hackerton.domain.challengeGroup.proofImage.entity.ProofImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProofImageService {

    private static String bucketName = "hackerton";

    private final AmazonS3Client amazonS3Client;
    private final ProofImageRepository proofImageRepository;

    @Transactional
    public List<ProofImage> saveImageList(List<MultipartFile> imageList, Proof proof) throws IOException {
        List<ProofImage> resultList = new ArrayList<>();
        for (MultipartFile multipartFile : imageList) {
            ProofImage proofImage = saveImage(multipartFile, proof);
            resultList.add(proofImage);
        }

        return resultList;
    }

    @Transactional
    public ProofImage saveImage(MultipartFile multipartFile, Proof proof) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        ProofImage proofImage = ProofImage.builder()
                .originName(originalFilename)
                .build();
        String filename = proofImage.getStoredName();
        System.out.println(filename);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getInputStream().available());
        amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

        proofImage.setProof(proof);

        String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
        proofImage.setAccessUrl(accessUrl);

        return proofImageRepository.save(proofImage);
    }


}
