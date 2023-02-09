package com.spring.green2209s_08.web.service.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.green2209s_08.web.exception.RedisDataNotFoundException;
import com.spring.green2209s_08.web.controller.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final RedisTemplate redisTemplate;

    public <T> boolean save(String key, T data){
        boolean result = false;
        try{
            ObjectMapper mapper = new ObjectMapper();
            String value = mapper.writeValueAsString(data);
            redisTemplate.opsForValue()
                    .set(key, value, Duration.ofMinutes(60));
        } catch (JsonProcessingException e) {
            log.error("parsing error occur: ", e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    private <T> T getData(String key, Class<T> classType) {
        String jsonResult = (String) redisTemplate.opsForValue().get(key);

        try{
            if (!Optional.ofNullable(jsonResult).isPresent()) {
                throw new RedisDataNotFoundException("데이터를 찾을 수 없습니다. 잘못된 토큰이거나, 이메일 전송 이후 1시간이 지났는지 확인해주세요.");
            }
            else {
                ObjectMapper mapper = new ObjectMapper();
                T obj = mapper.readValue(jsonResult, classType);
                return obj;
            }
        }catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeData(String key){
        redisTemplate.delete(key);
    }

    public MemberRegisterRequest validateToken(String token) {
        Optional<MemberRegisterRequest> findMember = Optional.ofNullable(
                getData(token, MemberRegisterRequest.class)
        );

        MemberRegisterRequest member = MemberRegisterRequest.builder()
                .name(findMember.get().getName())
                .birthdate(findMember.get().getBirthdate())
                .password(findMember.get().getPassword())
                .email(findMember.get().getEmail())
                .phoneNo(findMember.get().getPhoneNo())
                .build();

        return member;
    }
}
