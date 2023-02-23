package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.item.ItemConfirmListDto;
import com.spring.green2209s_08.web.controller.item.ItemListStatusCountResponse;
import com.spring.green2209s_08.web.controller.item.UploadItemCond;
import com.spring.green2209s_08.web.controller.item.VendorUploadItemResponse;
import com.spring.green2209s_08.web.service.dto.VendorHomeItemCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemViewRepository {
    VendorHomeItemCountResponse findUploadItemsCount(Long vendorId);

    Page<VendorUploadItemResponse> findUploadItemList(Long vendorId, Pageable pageable, UploadItemCond condition);

    ItemListStatusCountResponse findUploadItemStatusCount(Long vendorId);

    Page<ItemConfirmListDto> findItemConfirmList(Pageable pageable, UploadItemCond condition);

}
