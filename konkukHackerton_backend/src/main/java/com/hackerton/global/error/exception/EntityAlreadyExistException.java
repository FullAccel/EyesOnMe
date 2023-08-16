package com.hackerton.global.error.exception;

import com.hackerton.global.error.ErrorCode;

public class EntityAlreadyExistException extends BusinessException {

    public EntityAlreadyExistException( ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
