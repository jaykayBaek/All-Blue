package com.spring.green2209s_08.web.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemStatus {
    //심사중, 승인(판매중), 승인반려, 판매정지(관리자에의해), 판매중지(판매자 임의 설정가능)
    DECISION_IN_PROCESS("심사중"),
    APPROVAL("승인"),
    REJECT_APPROVAL("승인반려"),
    BLOCK_SELLING("판매정지"),
    SHUT_DOWN("판매중지");

    private final String description;
}
