package com.hackerton.global.error.exception;

import com.hackerton.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode,String message) {
        super(errorCode, message);
    }
}
