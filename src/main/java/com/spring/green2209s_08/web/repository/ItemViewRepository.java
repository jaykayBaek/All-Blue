package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.service.dto.ItemCountResponse;

public interface ItemViewRepository {
    ItemCountResponse findUploadItemsCount(Long vendorId);
}
