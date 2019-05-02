package com.haki.l4d2.manage.util.exception;

/**
 * 非法逻辑异常类
 */
public class IllegalException extends RuntimeException {

    public IllegalException(String message) {
        super(message);
    }

    public IllegalException(String message, Throwable cause) {
        super(message, cause);
    }
}
