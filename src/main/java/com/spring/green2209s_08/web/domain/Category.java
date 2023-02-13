package com.spring.green2209s_08.web.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int parentId;
    private String parentName;

    private int childId;
    private String childName;

    private int grandchildId;
    private String grandchildName;

    private Long itemId;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();
}