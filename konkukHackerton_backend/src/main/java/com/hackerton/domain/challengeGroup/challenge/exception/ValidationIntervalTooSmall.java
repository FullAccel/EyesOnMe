package com.hackerton.domain.challengeGroup.challenge.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class ValidationIntervalTooSmall extends BusinessException {

    public ValidationIntervalTooSmall(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationIntervalTooSmall(ErrorCode errorCode) {
        super(errorCode);
    }
}
