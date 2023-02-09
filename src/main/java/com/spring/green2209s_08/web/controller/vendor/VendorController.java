package com.spring.green2209s_08.web.controller.vendor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vendor")
public class VendorController {

    @GetMapping("join")
    public String vendorRegister(){
        return "main/vendor/registerForm";
    }

    @GetMapping("/join/welcome")
    public String welcome(){
        return "main/vendor/welcome";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "main/vendor/vendorLogin";
    }
}
