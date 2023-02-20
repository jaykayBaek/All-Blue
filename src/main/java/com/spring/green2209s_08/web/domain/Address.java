package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String recipient;
    private String phoneNo;
    private String zipcode;
    private String address;
    private String detail;

    public void changeAddress(String recipient, String phoneNo, String zipcode, String address, String detail) {
        this.recipient = recipient;
        this.phoneNo = phoneNo;
        this.zipcode = zipcode;
        this.address = address;
        this.detail = detail;
    }
}