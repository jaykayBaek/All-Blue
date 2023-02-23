package com.spring.green2209s_08.web.controller.admin;

import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ItemService itemService;

    @GetMapping("/home")
    public String home(Model model){
        itemService.findItemsOnDecisionInProcess();

        return "main/admin/adminHome";
    }

    @GetMapping("/item/confirm")
    public String confirmItem(Model model){
        itemService.findItemsOnDecisionInProcess();

        return "main/admin/confirmItem";
    }
}
