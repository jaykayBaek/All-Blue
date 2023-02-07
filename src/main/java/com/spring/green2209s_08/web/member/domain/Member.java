package com.spring.green2209s_08.web.member.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
    private MemberType memberType;

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
