package com.sergey.webquizengine.exceptions;

public class NoSuchQuizException extends RuntimeException {
    public NoSuchQuizException(String message) {
        super(message);
    }
}
