package com.hackerton.global.firebase.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class FirebaseTokenNotFoundException extends BusinessException {
    public FirebaseTokenNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
