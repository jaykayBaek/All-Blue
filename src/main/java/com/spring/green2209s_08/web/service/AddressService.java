package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.myhome.AddressRequest;
import com.spring.green2209s_08.web.controller.myhome.AddressResponse;
import com.spring.green2209s_08.web.domain.Address;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.exception.MemberAddressException;
import com.spring.green2209s_08.web.exception.errorResult.AddressErrorResult;
import com.spring.green2209s_08.web.repository.AddressRepository;
import com.spring.green2209s_08.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<AddressResponse> findAllMyAddress(Long memberId) {
        return addressRepository.findAllByMemberId(memberId).stream()
                .map(a -> new AddressResponse(a.getId(), a.getMember().getId(),a.getRecipient(), a.getPhoneNo(), a.getZipcode(), a.getAddress(), a.getDetail()))
                .collect(Collectors.toList());
    }

    public AddressResponse findByMemberIdAndId(Long memberId, Long addressId) {
        Optional<Address> findAddress = addressRepository.findByMemberIdAndId(memberId, addressId);

        if(findAddress.isEmpty()){
            throw new MemberAddressException(AddressErrorResult.ADDRESS_NOT_FOUND);
        }
        return new AddressResponse(findAddress.get().getId(), findAddress.get().getMember().getId(), findAddress.get().getRecipient(), findAddress.get().getPhoneNo(), findAddress.get().getZipcode(), findAddress.get().getAddress(), findAddress.get().getDetail());
    }

    @Transactional
    public void updateAddress(Long memberId, Long addressId, AddressRequest addressRequest) {
        Optional<Address> findAddress = addressRepository.findByMemberIdAndId(memberId, addressId);

        if(findAddress.isEmpty()){
            throw new MemberAddressException(AddressErrorResult.ADDRESS_NOT_FOUND);
        }

        Address address = findAddress.get();
        address.changeAddress(addressRequest.getRecipient(), addressRequest.getPhoneNo(), addressRequest.getZipcode(), addressRequest.getAddress(), addressRequest.getDetail());
    }

    public Address findById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new MemberAddressException(AddressErrorResult.ADDRESS_NOT_FOUND));
    }
}
