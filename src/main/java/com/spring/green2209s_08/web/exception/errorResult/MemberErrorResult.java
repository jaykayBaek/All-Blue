package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorResult {
    DUPLICATE_OR_LEAVED_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용 중이거나 회원 탈퇴된 이메일입니다."),
    LEAVE_PENALTY_PERIOD(HttpStatus.BAD_REQUEST, "회원탈퇴일로부터 30일이 지나지 않는 이메일로 가입할 수 없습니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호를 다시 확인하세요. 회원 가입되지 않은 이메일이거나, 이메일 또는 비밀번호를 잘못 입력하셨습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "비밀번호를 잘못 입력하셨습니다. 비밀번호를 다시 확인하세요."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 회원을 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"비정상적인 접근이거나, 세션이 만료되었습니다. 권한 인증을 위해 다시 로그인해주세요.");

    private final HttpStatus status;
    private final String message;
}
