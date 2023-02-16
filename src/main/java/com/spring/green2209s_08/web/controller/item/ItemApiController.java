package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
@Slf4j
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping("/list")
    public ResponseEntity<ItemListResponse> list(Pageable pageable, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);
        List<Item> content = itemService.findUploadItemList(vendorId, pageable).getContent();
        List<UploadItem> uploadItems = content.stream()
                .map(c -> UploadItem.builder()
                        .id(c.getId())
                        .itemName(c.getItemName())
                        .itemStatus(c.getItemStatus())
                        .price(c.getPrice() - c.getSalePrice())
                        .stockQuantity(c.getStockQuantity())
                        .uploadDate(c.getUploadDate())
                        .categoryName(c.getCategory().getGrandchildName())
                        .build())
                .collect(Collectors.toList());

        ItemListResponse itemListResponse = new ItemListResponse(
                HttpStatus.OK.toString(), "상품 전송 완료", "TRUE", uploadItems
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(itemListResponse);
    }
}
