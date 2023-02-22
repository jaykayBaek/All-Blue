package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.ItemQuery;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.repository.ItemRepository;
import com.spring.green2209s_08.web.repository.MemberRepository;
import com.spring.green2209s_08.web.repository.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryService {
    private final QueryRepository queryRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    public Long writeItemQuery(Long itemId, Long memberId, String content) {
        Item item = itemRepository.findById(itemId).get();
        Member member = memberRepository.findById(memberId).get();

        ItemQuery itemQuery = ItemQuery.builder()
                .item(item)
                .member(member)
                .content(content)
                .build();
        return queryRepository.save(itemQuery).getId();
    }
}
