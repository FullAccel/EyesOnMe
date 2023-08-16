package com.hackerton.global.error.exception;

import com.hackerton.global.error.ErrorCode;
import lombok.Getter;

import java.io.IOException;

@Getter
public class AWSS3Exception extends IOException {

    private ErrorCode errorCode;

    public AWSS3Exception(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AWSS3Exception(ErrorCode errorCode) {
        super("");
        this.errorCode = errorCode;
    }
}
