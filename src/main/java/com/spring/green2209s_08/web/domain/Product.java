package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Item{
    private String brandName;
}
