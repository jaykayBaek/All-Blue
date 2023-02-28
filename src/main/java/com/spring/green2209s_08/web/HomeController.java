package com.spring.green2209s_08.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping
    public String home(){
        return "main/main";
    }

}
