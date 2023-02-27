package com.spring.green2209s_08.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("MemberRegister")
@Builder
@AllArgsConstructor
public class MemberRegister {
    private String email;
    private String password;
    private String name;
    private String birthdate;
    private String phoneNo;
}
