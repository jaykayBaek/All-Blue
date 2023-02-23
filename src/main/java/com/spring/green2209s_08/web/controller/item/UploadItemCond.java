package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class UploadItemCond {
    private String query;
    private List<ItemStatus> itemStatus;
}
