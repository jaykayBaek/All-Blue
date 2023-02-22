package com.spring.green2209s_08.web.controller.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ItemSearchCond {
    private String query;
    private String parentId;
    private String grandchildId;
    private Integer page;
    private Integer pageSize;
    private String orderBy;
}
