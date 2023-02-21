package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.member.MemberRegisterRequest;
import com.spring.green2209s_08.web.controller.myhome.AddressRequest;
import com.spring.green2209s_08.web.domain.Address;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.enums.AccountType;
import com.spring.green2209s_08.web.exception.VendorException;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.domain.enums.MemberGrade;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import com.spring.green2209s_08.web.repository.AddressRepository;
import com.spring.green2209s_08.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;


    public void validateEmail(String email) {
        Optional<Member> findByEmailResult = memberRepository.findByEmail(email);
        if(findByEmailResult.isPresent()){
            Optional<LocalDateTime> getDate = Optional.ofNullable(findByEmailResult.get().getLeavedTime());
            if(isHadLogOfLeavedDate(getDate)){
                LocalDateTime leavedTime = getDate.get();
                LocalDateTime penaltyDate = LocalDateTime.now().minusDays(30);
                if(leavedTime.isAfter(penaltyDate)){
                    throw new MemberException(MemberErrorResult.LEAVE_PENALTY_PERIOD);
                }
            } else{
                throw new MemberException(MemberErrorResult.DUPLICATE_OR_LEAVED_EMAIL);
            }
            throw new MemberException(MemberErrorResult.DUPLICATE_OR_LEAVED_EMAIL);
        }
    }

    @Transactional
    public void register(MemberRegisterRequest request, String ipAddress) {
        Member member = Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .point(0)
                .phoneNo(request.getPhoneNo())
                .birthDate(request.getBirthdate())
                .accountType(AccountType.CUSTOMER)
                .memberGrade(MemberGrade.BRONZE)
                .createdTime(LocalDateTime.now())
                .createdIp(ipAddress)
                .accountLock(false)
                .leavedTime(null)
                .build();
        memberRepository.save(member);
    }

    public Long login(String email, String password) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if(isNotFoundMemberByEmail(member)){
            throw new MemberException(MemberErrorResult.LOGIN_FAIL);
        }

        Optional<LocalDateTime> getLeavedDate = Optional.ofNullable(member.get().getLeavedTime());

        if(isHadLogOfLeavedDate(getLeavedDate)){
            throw new MemberException(MemberErrorResult.LOGIN_FAIL);
        }

        if(isNotEqualPassword(password, member)){
            throw new MemberException(MemberErrorResult.LOGIN_FAIL);
        }

        return member.get().getId();
    }


    @Transactional
    public void updateVisitTimeAndIpAddress(Long memberId, LocalDateTime now, String remoteAddr) {
        Optional<Member> result = memberRepository.findById(memberId);
        result.get().updateVisitTimeAndIpAddress(now, remoteAddr);

    }



    /* --- ------ 가독성 메소드 ------ --- */
    private static boolean isNotFoundMemberByEmail(Optional<Member> member) {
        return member.isEmpty();
    }

    private boolean isNotEqualPassword(String password, Optional<Member> member) {
        return !passwordEncoder.matches(password, member.get().getPassword());
    }

    private static boolean isHadLogOfLeavedDate(Optional<LocalDateTime> getLeavedDate) {
        return getLeavedDate.isPresent();
    }

    public Member passwordCheck(Long memberId, String password) {
        Member findMember = memberRepository.findById(memberId).get();
        boolean matches = passwordEncoder.matches(password, findMember.getPassword());

        if(matches == false){
            throw new MemberException(MemberErrorResult.PASSWORD_NOT_MATCH);
        }

        return findMember;
    }

    @Transactional
    public void changeName(Long memberId, String name) {
        Member findMember = memberRepository.findById(memberId).get();
        findMember.changeName(name);
    }

    public void matchPasswordForChangePassword(String password, Long memberId) {
        Member findMember = memberRepository.findById(memberId).get();
        boolean result = passwordEncoder.matches(password, findMember.getPassword());

        if(result == false){
            throw new MemberException(MemberErrorResult.PASSWORD_NOT_MATCH);
        }
    }

    @Transactional
    public void changePassword(Long memberId, String newPassword) {
        Member findMember = memberRepository.findById(memberId).get();
        findMember.changePassword(newPassword);
    }

    @Transactional
    public void changePhoneNo(Long memberId, String phoneNo) {
        Member findMember = memberRepository.findById(memberId).get();
        findMember.changePhoneNo(phoneNo);
    }
}
