package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    private String parentId;
    private String parentName;

    private String childId;
    private String childName;

    private String grandchildId;
    private String grandchildName;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();
}