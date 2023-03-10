package com.spring.green2209s_08.web.controller.vandorManagement;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorHomeResponse;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.service.OrderService;
import com.spring.green2209s_08.web.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/management")
public class VendorManagementController {
    private final VendorService vendorService;
    private final OrderService orderService;

    @GetMapping("/delivery")
    public String deliveryManagement(HttpServletRequest request, Model model,
                                     @ModelAttribute ManageDeliveryCond condition, Pageable pageable){
        VendorHomeResponse response = getVendorHomeResponse(request);
        Long vendorId = response.getVendorId();
        model.addAttribute("vendor", response);


        List<ManageDeliveryDto> manageDeliveryList = orderService.manageDeliveryList(vendorId, pageable, condition);

        model.addAttribute("orderDeliveryList", manageDeliveryList);
        return "main/vendorManagement/deliveryManagement";
    }

    @GetMapping("/return")
    public String returnManagement(HttpServletRequest request, Model model){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendorManagement/returnManagement";
    }

    @GetMapping("/customer-inquiry")
    public String customerInquiryManagement(HttpServletRequest request, Model model){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendorManagement/customerInquiryManagement";
    }

    @GetMapping("/review")
    public String review(HttpServletRequest request, Model model){
        VendorHomeResponse response = getVendorHomeResponse(request);
        model.addAttribute("vendor", response);
        return "main/vendorManagement/review";
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
