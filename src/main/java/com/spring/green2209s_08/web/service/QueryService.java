package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.ItemQuery;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
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
        Optional<Item> findItem = itemRepository.findById(itemId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        boolean result = queryRepository.existsByItemIdAndMemberId(itemId, memberId);

        if(result==true){
            throw new ItemException(ItemErrorResult.ALREADY_WRITE_ITEM_QUERY);
        }

        if(findItem.isEmpty()){
            throw new ItemException(ItemErrorResult.ITEM_NOT_FOUND);
        }
        if(findMember.isEmpty()){
            throw new MemberException(MemberErrorResult.MEMBER_NOT_FOUND);
        }


        ItemQuery itemQuery = ItemQuery.builder()
                .item(findItem.get())
                .member(findMember.get())
                .content(content)
                .build();
        return queryRepository.save(itemQuery).getId();
    }
}
