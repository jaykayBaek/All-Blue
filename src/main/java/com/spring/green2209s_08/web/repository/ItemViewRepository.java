package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.item.UploadItemCond;
import com.spring.green2209s_08.web.controller.item.VendorUploadItemResponse;
import com.spring.green2209s_08.web.service.dto.ItemCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemViewRepository {
    ItemCountResponse findUploadItemsCount(Long vendorId);

    Page<VendorUploadItemResponse> findUploadItemList(Long vendorId, Pageable pageable, UploadItemCond condition);

    //    List<ItemsOnDecisionInProcessDto> findItemsOnDecisionInProcess();
}
