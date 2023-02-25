package com.spring.green2209s_08.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.Wishlist;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.repository.ItemRepository;
import com.spring.green2209s_08.web.repository.MemberRepository;
import com.spring.green2209s_08.web.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Cookie addWishlist(Long itemId, Integer quantity, HttpServletRequest request) throws JsonProcessingException {
        Map<Long, Integer> newWishlist = new HashMap<>();
        newWishlist.put(itemId, quantity);

        Cookie[] cookies = request.getCookies();
        if(isHadCookies(cookies)){
            for (Cookie cookie : cookies) {
                if(isHadCookieInWishlist(cookie)){
                    newWishlist = addCookieItemIdInWishlist(cookie, newWishlist);
                }
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(newWishlist);

        // 그냥 쿠키에 저장하면 json으로 파싱해서 큰따음표(") 때문에 예외 발생함. encoding 필요
        String encodedMap = Base64.getEncoder().encodeToString(jsonMap.getBytes(StandardCharsets.UTF_8));

        Cookie cookie = new Cookie(SessionConst.WISHLIST, encodedMap);
        cookie.setMaxAge(60*60*24*31);
        cookie.setDomain(SessionConst.COOKIE_DOMAIN);
        cookie.setPath(SessionConst.COOKIE_PATH);
        return cookie;
    }

    private Map<Long, Integer> addCookieItemIdInWishlist(Cookie cookie, Map<Long, Integer> newWishlist) throws JsonProcessingException {
        String jsonCookie = cookie.getValue();

        String decodedJsonCookie = new String(Base64.getDecoder().decode(jsonCookie), StandardCharsets.UTF_8);

        Map<Long, Integer> oldWishlist = new ObjectMapper().readValue(decodedJsonCookie, new TypeReference<Map<Long, Integer>>() {});
        oldWishlist.putAll(newWishlist);

        return oldWishlist;
    }

    private static boolean isHadCookieInWishlist(Cookie cookie) {
        return cookie.getName().equals(SessionConst.WISHLIST);
    }

    private static boolean isHadCookies(Cookie[] cookies) {
        return cookies != null;
    }

    public List<WishlistViewDto> findWishlist(WishlistCond condition) {
        return wishlistRepository.findWishlist(condition);
    }

    @Transactional
    public void addWishlist(Long itemId, Integer quantity, Long memberId) {
        Item findItem = itemRepository.findById(itemId).get();
        Member findMember = memberRepository.findById(memberId).get();
        Wishlist wishlist = Wishlist.builder()
                .item(findItem)
                .member(findMember)
                .selectedQuantity(quantity)
                .build();
        wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeItemInWishlist(Long itemId, Long memberId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        log.info("remove={}", findItem.isEmpty());
        log.info("remove={}", findItem.get().getId());
        log.info("------------------------");

        Optional<Member> findMember = memberRepository.findById(memberId);
        log.info("remove={}", findMember.isEmpty());
        log.info("remove={}", findMember.get().getId());
        log.info("------------------------");


        if(findItem.isEmpty()){
            throw new ItemException(ItemErrorResult.ITEM_NOT_FOUND);
        }
        if(findMember.isEmpty()){
            throw new MemberException(MemberErrorResult.MEMBER_NOT_FOUND);
        }
        log.info("------------------------");

        Wishlist findWishlist = wishlistRepository.findByItemIdAndMemberId(itemId, memberId);
        log.info("findWishlist = {}", findWishlist.getId());
        wishlistRepository.delete(findWishlist);

        log.info("------------------------");
    }
}
