package com.spring.green2209s_08.web.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorResult {
    DUPLICATE_OR_LEAVED_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용 중이거나 회원 탈퇴된 이메일입니다."),
    LEAVE_PENALTY_PERIOD(HttpStatus.BAD_REQUEST, "회원탈퇴일로부터 30일이 지나지 않는 이메일로 가입할 수 없습니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호를 다시 확인하세요. 회원 가입되지 않은 이메일이거나, 이메일 또는 비밀번호를 잘못 입력하셨습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
