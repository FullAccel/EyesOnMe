package com.hackerton.domain.follows.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowResponseByRequestDto {

    private Long followerId;

    private boolean isAccepted;

    @Builder
    public FollowResponseByRequestDto(Long followerId, boolean isAccepted) {
        this.followerId = followerId;
        this.isAccepted = isAccepted;
    }
}
