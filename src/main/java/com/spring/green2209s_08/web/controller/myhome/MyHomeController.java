package com.spring.green2209s_08.web.controller.myhome;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.repository.OrderItemRepository;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.OrderItemService;
import com.spring.green2209s_08.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home")
public class MyHomeController {
    private final OrderItemRepository orderItemRepository;

    private final AddressService addressService;
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/cancel-return-exchange/list")
    public String cancelReturnExchangeList(){
        return "main/myhome/cancelReturnExchangeList";
    }

    @GetMapping("/my-address/list")
    public String myAddressList(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId, Model model){
        List<AddressResponse> findAddresses = addressService.findAllMyAddress(memberId);

        model.addAttribute("addresses", findAddresses);
        return "main/myhome/myAddressList";
    }

    @GetMapping("/my-address/form/add")
    public String myAddressAdd(){
        return "main/myhome/myAddressAddForm";
    }

    @GetMapping("/my-address/{addressId}/edit")
    public String myAddressEdit(@PathVariable Long addressId, Model model,
                                @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        AddressResponse address = addressService.findByMemberIdAndId(memberId, addressId);

        model.addAttribute("address", address);
        return "main/myhome/myAddressEditForm";
    }

    @GetMapping("/review/list")
    public String reviewListHome(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId, Model model){
        Member findMember = memberService.findById(memberId);
        List<ReviewItemDto> reviewItems = orderItemService.reviewPage(memberId);

        MemberDto memberDto = new MemberDto(
                findMember.getId(), findMember.getName()
        );

        model.addAttribute("member", memberDto);
        model.addAttribute("reviewItems", reviewItems);
        return "main/myhome/reviewHome";
    }

    @GetMapping("/manage/review")
    public String manageReview(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                               Model model){
        Member findMember = memberService.findById(memberId);
        List<ReviewItemDto> wroteReviewItems = orderItemService.reviewPageWrote(memberId);
        Long itemId = wroteReviewItems.get(0).getItemId();
        log.info("itemId={}", itemId);
        MemberDto memberDto = new MemberDto(
                findMember.getId(), findMember.getName()
        );

        model.addAttribute("member", memberDto);
        model.addAttribute("wroteReviewItems", wroteReviewItems);
        return "main/myhome/wroteReview";
    }

    @GetMapping("/review/write/{itemId}")
    public String reviewWrite(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                              @PathVariable Long itemId, Model model){
        orderService.findOrderForReview(memberId, itemId);
        model.addAttribute("itemId", itemId);
        return "main/myhome/reviewWrite";
    }

}