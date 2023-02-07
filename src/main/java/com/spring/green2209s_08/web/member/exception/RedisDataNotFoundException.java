package com.spring.green2209s_08.web.member.exception;

public class RedisDataNotFoundException extends IllegalArgumentException{
    public RedisDataNotFoundException(String message) {
        super(message);
    }
}
