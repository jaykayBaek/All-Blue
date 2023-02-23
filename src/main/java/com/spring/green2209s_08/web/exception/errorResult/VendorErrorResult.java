package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VendorErrorResult {
    DUPLICATE_OR_LEAVED_LOGIN_ID(HttpStatus.BAD_REQUEST, "이미 사용 중이거나 회원 탈퇴된 파트너 아이디입니다."),
    ADD_NEGATIVE_STOCK_QUANTITY(HttpStatus.BAD_REQUEST, "재고를 음수로 추가할 수 없습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "재고가 충분하지 않습니다."),
    OVER_UPLOAD_IMAGE(HttpStatus.BAD_REQUEST, "업로드할 수 있는 이미지를 초과했습니다."),
    LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호를 다시 확인하세요. 회원 가입되지 않은 아이디이거나 또는 비밀번호를 잘못 입력하셨습니다."),
    ALREADY_LOGIN(HttpStatus.BAD_REQUEST ,"이미 로그인되었습니다"),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "비밀번호를 잘못 입력하셨습니다. 비밀번호를 다시 확인하세요."),
    CHANGE_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "변경하고자 하는 비밀번호를 다시 확인하세요."),
    ALREADY_ADD_LICENSE(HttpStatus.BAD_REQUEST, "이미 상세정보 등록을 완료하셨습니다.");
    private final HttpStatus status;
    private final String message;
}
