package com.spring.green2209s_08.web.exception;

public class RedisDataNotFoundException extends IllegalArgumentException{
    public RedisDataNotFoundException(String message) {
        super(message);
    }
}
