package com.spring.green2209s_08.web.controller.vendor;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorHomeResponse;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorInfoChangeRequest;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.VendorLicense;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.VendorLicenseService;
import com.spring.green2209s_08.web.service.VendorService;
import com.spring.green2209s_08.web.service.dto.VendorHomeItemCountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;
    private final ItemService itemService;
    private final VendorLicenseService vendorLicenseService;

    @GetMapping("/join")
    public String vendorRegister(){
        return "main/vendor/registerForm";
    }

    @GetMapping("/join/welcome")
    public String welcome(){
        return "main/vendor/welcome";
    }

    @GetMapping("/login")
    public String loginForm(@SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){

        if(vendorId != null){
            return "redirect:/";
        }

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
        Long vendorId = response.getVendorId();
        VendorHomeItemCountResponse uploadItemsCount = itemService.findUploadItemsCountInVendorHome(vendorId);
        boolean result = vendorService.isLicenseVendor(vendorId);

        model.addAttribute("isConfirmLicense", result);
        model.addAttribute("vendor", response);
        model.addAttribute("uploadItemsCount", uploadItemsCount);

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

    @GetMapping("/account/change-info")
    public String confirmPassword(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendorAccount/changeInfo";
    }
    @PostMapping("/account/change-info")
    public String changeVendorInfo(@ModelAttribute VendorInfoChangeRequest changeRequest,
                                   @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){
        vendorService.changeVendorInfo(vendorId, changeRequest);

        return "redirect:/vendor/account/change-info";
    }

    @GetMapping("/account/change-password")
    public String changePasswordForm(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendorAccount/changePassword";
    }
    @GetMapping("/account/confirm/license")
    public String confirmLicenseForm(Model model, HttpServletRequest request){
        VendorHomeResponse response = getVendorHomeResponse(request);

        Long vendorId = response.getVendorId();
        Optional<VendorLicense> findLicense = vendorLicenseService.findByVendorId(vendorId);
        if(findLicense.isPresent()){
            VendorLicenseDto vendorLicenseDto = findLicense
                    .map(l -> new VendorLicenseDto(l.getId(), l.getLicenseNo(),
                            l.getStoreAddress().getAddress(), l.getStoreAddress().getZipcode(), l.getStoreAddress().getDetail(),
                            l.getStoreName())).get();
            model.addAttribute("vendorLicenseDto", vendorLicenseDto);
        }

        model.addAttribute("vendor", response);
        return "main/vendorAccount/confirmLicense";
    }

    private VendorHomeResponse getVendorHomeResponse(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        Optional<Vendor> findVendor = vendorService.findById(vendorId);

        Optional<VendorHomeResponse> vendorHomeResponse = findVendor.map(m -> new VendorHomeResponse(
                vendorId, m.getVendorLoginId(), m.getVendorName(), m.getVendorEmail(), m.getVendorPhoneNo()
        ));

        VendorHomeResponse response = vendorHomeResponse.get();
        return response;
    }

}
