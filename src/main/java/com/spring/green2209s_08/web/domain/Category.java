package com.spring.green2209s_08.web.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Category {
    private int parent_id;
    private int child_id;
}