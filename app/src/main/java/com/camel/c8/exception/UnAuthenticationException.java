package com.camel.c8.exception;

public class UnAuthenticationException extends RuntimeException {
    public UnAuthenticationException() {
        super("未登录");
    }
}
