package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.exception.VendorErrorResult;
import com.spring.green2209s_08.web.exception.VendorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
                    .storedFileName(i+"")
                    .uploadFileName(i+"")
                    .build();
            images.add(file);
        }
        Fish fish = Fish.getFish("hello", FishSex.MALE, "미디움", "금붕어",
                10000, 2999, 30);
        //when
//        VendorException e = assertThrows(VendorException.class, () ->
//                itemService.addFish(fish, images)
//        );

        //then
//        assertThat(e.getErrorResult()).isEqualTo(VendorErrorResult.OVER_UPLOAD_IMAGE);
    }

}