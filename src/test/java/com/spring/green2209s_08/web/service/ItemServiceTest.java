package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.item.api.EditFishRequest;
import com.spring.green2209s_08.web.controller.item.api.EditNumberRequest;
import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    void 상품등록실패_8장등록1() {
        //given
        List<ItemImage> images = new ArrayList<>();
        for(int i = 0; i<9; i++){
            ItemImage file = ItemImage.builder()
                    .id((long) i)
                    .thumbnailImage(false)
                    .originalImageName(i+"")
                    .savedImageName(i+"")
                    .build();
            images.add(file);
        }
        Fish fish = Fish.getFish("hello", FishSex.MALE, "미디움", "금붕어",
                10000, 2999, 30, "hello", LocalDate.now());
        //when
//        VendorException e = assertThrows(VendorException.class, () ->
//                itemService.addFish(fish, images)
//        );

        //then
//        assertThat(e.getErrorResult()).isEqualTo(VendorErrorResult.OVER_UPLOAD_IMAGE);
    }

    @Test
    void 상품수정시도_다른벤더Id() {
        //given
        Vendor vendor = Vendor.builder().id(1L).build();
        Fish fish = Fish.getFish("1", FishSex.MALE, "m", "1",
                5, 5, 5, "1", LocalDate.now());
        fish.assignVendor(vendor);
        itemService.addItem(fish);

        //when
        Vendor tester = Vendor.builder().id(2L).build();

        ItemException e = assertThrows(ItemException.class, () ->
                itemService.validateVendorIdToItem(tester.getId(), 1L)
        );

        //then
        assertThat(e.getErrorResult().getMessage()).isEqualTo(ItemErrorResult.ITEM_EDIT_FAIL_VENDOR_NOT_MATCH.getMessage());

    }

    @Test
    void 상품수정시도_해당하는id의상품이없음() {
        //given
        Vendor vendor = Vendor.builder().id(1L).build();
        Fish fish = Fish.getFish("1", FishSex.MALE, "m", "1",
                5, 5, 5, "1", LocalDate.now());
        fish.assignVendor(vendor);
        itemService.addItem(fish);

        //when
        Vendor tester = Vendor.builder().id(1L).build();

        ItemException e = assertThrows(ItemException.class, () ->
                itemService.validateVendorIdToItem(tester.getId(), 2L)
        );

        //then
        assertThat(e.getErrorResult().getMessage()).isEqualTo(ItemErrorResult.ITEM_NOT_FOUND.getMessage());

    }

    @Test
    void 상품수정시도_성공() {
        //given
        Vendor vendor = Vendor.builder().id(1L).build();
        Fish fish = Fish.getFish("1", FishSex.MALE, "m", "1",
                5, 5, 5, "1", LocalDate.now());
        fish.assignVendor(vendor);
        itemService.addItem(fish);

        //when
        Vendor tester = Vendor.builder().id(1L).build();

        ItemException e = assertThrows(ItemException.class, () ->
                itemService.validateVendorIdToItem(tester.getId(), 1L)
        );

        //then
        assertThat(e.getErrorResult().getMessage()).isEqualTo(ItemErrorResult.ITEM_NOT_FOUND.getMessage());

    }

    @Test
    void 상품수정_노출상품명() {
        //given
        Fish fish = Fish.getFish("1", FishSex.MALE, "1", "name", 1, 1, 1, "1", LocalDate.now());
        itemService.addItem(fish);
        //when
        itemService.changeItemName(fish.getId(), "test");

        //then
        assertThat(fish.getItemName()).isEqualTo("test");

    }

    @Test
    void 상품수정_금액및수량() {
        //given
        Fish fish = Fish.getFish("1", FishSex.MALE, "1", "name", 1, 1, 1, "1", LocalDate.now());
        itemService.addItem(fish);

        EditNumberRequest request = EditNumberRequest.builder()
                        .itemId(fish.getId())
                        .price(2)
                        .salePrice(2)
                        .stockQuantity(2)
                        .deliveryPrice(2)
                        .build();
        //when
        itemService.changeNumber(request);

        //then
        assertThat(fish.getPrice()).isEqualTo(request.getPrice());
        assertThat(fish.getStockQuantity()).isEqualTo(request.getStockQuantity());
        assertThat(fish.getDeliveryPrice()).isEqualTo(request.getDeliveryPrice());
    }

    @Test
    void 상품수정_세부정보변경() {
        //given
        Fish fish = Fish.getFish("1", FishSex.MALE, "1", "name", 1, 1, 1, "1", LocalDate.now());
        itemService.addItem(fish);

        EditFishRequest request = new EditFishRequest(fish.getId(), "MALE", "tester", "XL");

        //when
        itemService.changeFish(request);

        //then
        assertThat(fish.getBreederName()).isEqualTo(request.getBreederName());
        assertThat(fish.getSex()).isEqualTo(FishSex.MALE);
    }

}