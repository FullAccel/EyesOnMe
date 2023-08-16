package com.hackerton.domain.challengeGroup.validation.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class ValidatorNumOverFlowException extends BusinessException {
    public ValidatorNumOverFlowException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ValidatorNumOverFlowException(ErrorCode errorCode) {
        super(errorCode);
    }
}
