package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.service.ItemSearchService;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final ItemSearchService itemSearchService;
    private final ItemService itemService;

    @GetMapping
    public String view(@ModelAttribute ItemSearchCond condition, Pageable pageable, Model model){
        Page<ItemDto> items = itemSearchService.findItemsByCond(condition, pageable);

        for (ItemDto item : items) {
            System.out.println("item = " + item);
        }
        model.addAttribute("items", items);
        model.addAttribute("query", condition.getQuery());

        return "main/search/search";
    }

    @GetMapping("/detail/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model){
//        itemService.itemDetails();
        return "main/search/detail";
    }


}
