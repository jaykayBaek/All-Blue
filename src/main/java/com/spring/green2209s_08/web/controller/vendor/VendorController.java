package com.spring.green2209s_08.web.controller.vendor;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.repository.VendorRepository;
import com.spring.green2209s_08.web.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/join")
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

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);

        return "main/vendor/vendorHome";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendor/dashboard";
    }

    @GetMapping("/vendor-inventory/form")
    public String inventoryForm(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendor/inventoryForm";
    }

    private VendorHomeResponse getVendorHomeResponse(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        Optional<Vendor> findVendor = vendorService.findById(vendorId);

        Optional<VendorHomeResponse> vendorHomeResponse = findVendor.map(m -> new VendorHomeResponse(
                m.getVendorLoginId(), m.getVendorName(), m.getVendorEmail(), m.getVendorPhoneNo()
        ));

        VendorHomeResponse response = vendorHomeResponse.get();
        return response;
    }

}
