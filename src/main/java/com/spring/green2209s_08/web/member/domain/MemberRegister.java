package com.spring.green2209s_08.web.member.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash("Member")
@Builder
@AllArgsConstructor
public class MemberRegister {
    private String email;
    private String password;
    private String name;
    private String birthdate;
    private String phoneNo;
}
