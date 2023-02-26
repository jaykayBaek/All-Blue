package com.spring.green2209s_08.web.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FishSex {
    MALE("수컷"), FEMALE("암컷"), UNKNOWN("성별미상");

    private String description;
}
