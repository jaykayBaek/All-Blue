package com.spring.green2209s_08.web.controller.myhome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequest {
    private Long itemId;
    private String headLine;
    private String content;
    private Long reviewRating;
}
