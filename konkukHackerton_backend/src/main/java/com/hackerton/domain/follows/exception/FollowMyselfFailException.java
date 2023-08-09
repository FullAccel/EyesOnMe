package com.hackerton.domain.follows.exception;

import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.BusinessException;

public class FollowMyselfFailException extends BusinessException {
    public FollowMyselfFailException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
