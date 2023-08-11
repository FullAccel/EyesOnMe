package com.hackerton.domain.challengeGroup.check.entity;

import com.hackerton.domain.challengeGroup.proof.entity.Proof;
import com.hackerton.domain.challengeGroup.validation.entity.Validation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "checks")
public class Check {

    @Id
    @Column(name = "CHECK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "check")
    private List<Proof> proofs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "VALIDATION_ID")
    private Validation validation;
}
