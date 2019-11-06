package com.jupiter.ts.exception;

public class CustomizeException extends RuntimeException {

    private Integer status;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.status=errorCode.getStatus();
        this.message = errorCode.getMsg();
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }
}
