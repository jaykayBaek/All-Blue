package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;
import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BusinessRegistration {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_registration_id")
    private Long id;
    private String businessRegistrationNo; //사업자등록번호
    private String companyName; //사명
    private String ceoName; //대표자이름
    private String imgBusinessRegistration; //사업자등록증이미지

    @Embedded
    private VendorAddress address;

    @OneToOne(mappedBy = "businessRegistration", fetch = LAZY)
    private Vendor vendor;
}
