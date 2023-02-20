package com.spring.green2209s_08.web.controller.myhome;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home")
public class MyHomeController {

    private final AddressService addressService;

    @GetMapping("/order/list")
    public String orderList(){
        return "main/myhome/orderlist";
    }

    @GetMapping("/cancel-return-exchange/list")
    public String cancelReturnExchangeList(){
        return "main/myhome/cancelReturnExchangeList";
    }

    @GetMapping("/my-address/list")
    public String myAddressList(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        List<AddressResponse> findAddresses = addressService.findAllMyAddress(memberId);

        model.addAttribute("addresses", findAddresses);
        return "main/myhome/myAddressList";
    }

    @GetMapping("/my-address/form/add")
    public String myAddressAdd(){
        return "main/myhome/myAddressAddForm";
    }
    @GetMapping("/my-address/{addressId}/edit")
    public String myAddressEdit(@PathVariable Long addressId, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        AddressResponse address = addressService.findByMemberIdAndId(memberId, addressId);

        model.addAttribute("address", address);
        return "main/myhome/myAddressEditForm";
    }


}
