package com.spring.green2209s_08.web.controller.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ItemListResponse {
    private final String CODE;
    private final String MESSAGE;
    private final String SUCCESS;
    private final List<UploadItem> uploadItem;
}
