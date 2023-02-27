package com.spring.green2209s_08.web.controller.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class OrderSearchCond {
    private OrderDateEnum orderDate;
    private String query;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    public void createItemCond(LocalDateTime today, LocalDateTime conditionDate){
        this.startDate = conditionDate;
        this.endDate = today;
    }
}