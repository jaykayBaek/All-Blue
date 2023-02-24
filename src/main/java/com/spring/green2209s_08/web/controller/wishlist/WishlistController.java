package com.spring.green2209s_08.web.controller.wishlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final ItemService itemService;

    @GetMapping
    public String wishList(@CookieValue(name = SessionConst.WISHLIST) Cookie cookie,
                           Model model, HttpServletRequest request) throws JsonProcessingException {
        List<Long> itemIdList = new ArrayList<>();
        boolean isLogin = false;

        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        if(isHadItemCookieInWishlist(cookie)){
            addItemIdToList(cookie, itemIdList);
        }

        WishlistCond condition = new WishlistCond(memberId, itemIdList);

        List<WishlistViewDto> items = new ArrayList<>();

        if(isNotLoginMember(memberId)){
            Map<Long, Integer> cookieWishlist = getCookieWishlist(cookie);
            if(cookieWishlist.size() != 0){
                items = itemService.findWishlist(condition, cookieWishlist);
            }

        } else{
            items = wishlistService.findWishlist(condition);
            isLogin = true;
        }

        model.addAttribute("items", items);
        model.addAttribute("isLogin", isLogin);


        return "main/wishlist/wishlist";
    }


    private static void addItemIdToList(Cookie cookie, List<Long> itemIdList) throws JsonProcessingException {
        Map<Long, Integer> cookieWishlist = getCookieWishlist(cookie);

        for (Long itemId : cookieWishlist.keySet()) {
            itemIdList.add(itemId);
        }
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

    private static boolean isHadItemCookieInWishlist(Cookie cookie) {
        return cookie != null;
    }

    private static boolean isNotLoginMember(Long memberId) {
        return memberId == null;
    }

}
