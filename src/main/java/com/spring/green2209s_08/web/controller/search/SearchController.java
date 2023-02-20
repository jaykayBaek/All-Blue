package com.spring.green2209s_08.web.controller.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    @GetMapping
    public String view(){
        return "main/search/search";
    }

    @GetMapping("/detail/{itemId}")
    public String itemDetail(@PathVariable Long itemId){

        return "main/search/detail";
    }

}
