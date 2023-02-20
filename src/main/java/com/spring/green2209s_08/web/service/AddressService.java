package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.myhome.AddressRequest;
import com.spring.green2209s_08.web.domain.Address;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.repository.AddressRepository;
import com.spring.green2209s_08.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addAddress(Long memberId, AddressRequest request) {
        Member findMember = memberRepository.findById(memberId).get();
        Address address = Address.builder()
                .member(findMember)
                .recipient(request.getRecipient())
                .phoneNo(request.getPhoneNo())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .detail(request.getDetail())
                .build();
        addressRepository.save(address);
    }
}
