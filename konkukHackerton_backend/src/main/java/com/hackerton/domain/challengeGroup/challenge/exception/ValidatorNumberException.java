package com.hackerton.domain.challengeGroup.challenge.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class ValidatorNumberException extends BusinessException {
    public ValidatorNumberException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ValidatorNumberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
