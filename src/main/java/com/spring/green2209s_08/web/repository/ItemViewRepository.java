package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.item.ItemListStatusResponse;
import com.spring.green2209s_08.web.controller.item.UploadItemCond;
import com.spring.green2209s_08.web.controller.item.VendorUploadItemResponse;
import com.spring.green2209s_08.web.service.dto.VendorHomeItemCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemViewRepository {
    VendorHomeItemCountResponse findUploadItemsCount(Long vendorId);

    Page<VendorUploadItemResponse> findUploadItemList(Long vendorId, Pageable pageable, UploadItemCond condition);

    ItemListStatusResponse findUploadItemStatusCount(Long vendorId);

    //    List<ItemsOnDecisionInProcessDto> findItemsOnDecisionInProcess();
}
