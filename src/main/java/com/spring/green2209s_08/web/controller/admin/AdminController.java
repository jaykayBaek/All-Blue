package com.spring.green2209s_08.web.controller.admin;

import com.spring.green2209s_08.web.controller.item.ItemConfirmListDto;
import com.spring.green2209s_08.web.controller.item.UploadItemCond;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ItemService itemService;

    @GetMapping("/home")
    public String home(Model model){

        return "main/admin/adminHome";
    }

    @GetMapping("/item/confirm")
    public String confirmItem(@PageableDefault(size=50) Pageable pageable,
                              @ModelAttribute UploadItemCond condition, Model model){
        Page<ItemConfirmListDto> items = itemService.findItemConfirmList(pageable, condition);


        model.addAttribute("items", items);
        model.addAttribute("condition", condition);
        return "main/admin/confirmItem";
    }
}
