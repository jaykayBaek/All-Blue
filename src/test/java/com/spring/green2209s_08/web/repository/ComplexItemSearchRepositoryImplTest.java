package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.search.ItemDto;
import com.spring.green2209s_08.web.controller.search.ItemSearchCond;
import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.Product;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.ItemSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Transactional
@SpringBootTest
class ComplexItemSearchRepositoryImplTest {

    @Autowired
    private ItemSearchService itemSearchService;

    @Autowired
    private EntityManager em;

    @Test
    void searchItems() {
        //given
        Fish fish1 = Fish.getFish("1", FishSex.MALE, "m", "test",
                5000, 1000, 1000, "test", LocalDate.now()
        );

        Fish fish2 = Fish.getFish("1", FishSex.MALE, "m", "test",
                5000, 1000, 1000, "test", LocalDate.now()
        );

        Product product1 = Product.getProduct("1", "test",
                5000, 1000, 1000, "test", LocalDate.now());

        Product product2 = Product.getProduct("1", "test",
                5000, 1000, 1000, "test", LocalDate.now());

        fish1.updateItemStatus(ItemStatus.APPROVAL);
        fish2.updateItemStatus(ItemStatus.APPROVAL);
        product1.updateItemStatus(ItemStatus.APPROVAL);
        product2.updateItemStatus(ItemStatus.APPROVAL);

        em.persist(fish1);
        em.persist(fish2);
        em.persist(product1);
        em.persist(product2);

        //when

        List<ItemDto> itemsByCond = itemSearchService.findItemsByCond(
                new ItemSearchCond(null, null, null, 0, 10, ""));

        //then
        for (ItemDto itemDto : itemsByCond) {
            System.out.println("itemDto = " + itemDto.getItemName());
        }

    }
}