package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.Product;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.dto.VendorHomeItemCountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemViewRepositoryImplTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private EntityManager em;

    @Test
    void 상품등록개수() {
        //given
        Vendor vendor = Vendor.builder()
                .vendorName("홍길동")
                .build();
        em.persist(vendor);

        Fish fish1 = Fish.getFish("1", FishSex.MALE, "m", "test",
                5000, 1000, 1000, 5, "test", LocalDate.now()
        );

        Fish fish2 = Fish.getFish("1", FishSex.MALE, "m", "test",
                5000, 1000, 1000, 5,"test", LocalDate.now()
        );

        Product product1 = Product.getProduct("1", "test",
                5000, 1000, 1000, 5,"test", LocalDate.now());

        Product product2 = Product.getProduct("1", "test",
                5000, 1000, 1000, 5,"test", LocalDate.now());

        fish1.updateItemStatus(ItemStatus.APPROVAL);
        fish1.assignVendor(vendor);
        fish2.assignVendor(vendor);
        product1.assignVendor(vendor);
        product2.assignVendor(vendor);

        em.persist(fish1);
        em.persist(fish2);
        em.persist(product1);
        em.persist(product2);

        //when
        VendorHomeItemCountResponse itemCount = itemService.findUploadItemsCountInVendorHome(vendor.getId());

        //then
        assertThat(itemCount.getApprovalItemsCount()).isEqualTo(1);
        assertThat(itemCount.getUploadItemsCount()).isEqualTo(4);

    }
}