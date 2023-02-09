package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long id;
    private String vendorLoginId;
    private String vendorPassword;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToOne
    @JoinColumn(name = "business_registration_id")
    private BusinessRegistration businessRegistration;
}