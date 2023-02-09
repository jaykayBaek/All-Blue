package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.AccountType;
import com.spring.green2209s_08.web.domain.enums.MemberGrade;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20)
    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    private int point;

    private String phoneNo;

    private String birthDate;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;

    @OneToMany(mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();

    private LocalDateTime createdTime;
    private LocalDateTime lastVisitedTime;
    private String createdIp;
    private String lastVisitedIp;

    private Boolean accountLock;

    private LocalDateTime leavedTime;

    public void updateVisitTimeAndIpAddress(LocalDateTime now, String remoteAddr) {
        this.lastVisitedTime = now;
        this.lastVisitedIp = remoteAddr;
    }
}
