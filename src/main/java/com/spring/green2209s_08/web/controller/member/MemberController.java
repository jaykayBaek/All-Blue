package com.spring.green2209s_08.web.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.exception.RedisDataNotFoundException;
import com.spring.green2209s_08.web.repository.MemberRepository;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.WishlistService;
import com.spring.green2209s_08.web.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final RegisterService registerService;
    private final MemberRepository memberRepository;
    private final WishlistService wishlistService;

    @GetMapping("/signup")
    public String signup(){
        return "main/member/registerForm";
    }

    @GetMapping("/email/verify")
    public String emailCommit(
            @RequestParam String token, HttpServletRequest request,
            @CookieValue(name= SessionConst.WISHLIST, required = false) Cookie cookie) throws JsonProcessingException {

        MemberRegisterRequest memberRegisterRequest = registerService.validateToken(token);
        String ipAddress = request.getRemoteAddr();
        Member member = memberService.register(memberRegisterRequest, ipAddress);
        registerService.removeData(token);


        Optional<String> optionalCookie = Optional.ofNullable(cookie)
                .map(Cookie::getValue);
        if(optionalCookie.isPresent()){
            Map<Long, Integer> cookieWishlist = getCookieWishlist(cookie);
            wishlistService.saveCookieWishlistToDatabase(cookieWishlist, member.getId());
            cookie.setMaxAge(0);
        }

        return "main/member/emailVerify";
    }

    @ExceptionHandler(RedisDataNotFoundException.class)
    public String handleMemberException(RedisDataNotFoundException e){
        return "/error/custom/tokenNotFound";
    }

    @GetMapping("/email/send")
    public String emailSend(String email,
                            Model model){
        model.addAttribute("email", email);
        return "main/member/emailSend";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/account/modify")
    public String accountModifyForm(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                                    Model model){

        Member findMember = memberRepository.findById(memberId).get();

        String email = findMember.getEmail();
        model.addAttribute("email", email);
        return "main/member/editMemberInfo";
    }



    private static Map<Long, Integer> getCookieWishlist(Cookie cookie) throws JsonProcessingException {
        String decodedJsonCookie = getDecodedJsonCookie(cookie);
        Map<Long, Integer> cookieWishlist = new ObjectMapper().readValue(decodedJsonCookie, new TypeReference<Map<Long, Integer>>() {});
        return cookieWishlist;
    }

    private static String getDecodedJsonCookie(Cookie cookie) {
        String encodedJsonCookie = cookie.getValue();
        String decodedJsonCookie = new String(Base64.getDecoder().decode(encodedJsonCookie), StandardCharsets.UTF_8);
        return decodedJsonCookie;
    }

    private static void addItemIdToList(Cookie cookie, List<Long> itemIdList) throws JsonProcessingException {
        Map<Long, Integer> cookieWishlist = getCookieWishlist(cookie);

        for (Long itemId : cookieWishlist.keySet()) {
            itemIdList.add(itemId);
        }
    }



}