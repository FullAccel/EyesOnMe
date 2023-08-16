package com.hackerton.domain.challengeGroup.challenge.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class ValidationCountOverflowException extends BusinessException {
    public ValidationCountOverflowException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationCountOverflowException(ErrorCode errorCode) {
        super(errorCode);
    }
}
