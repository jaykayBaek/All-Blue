package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vendor extends CommonEntity {
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

    @OneToMany(mappedBy = "vendor")
    private List<Item> items = new ArrayList<>();

    @OneToOne(mappedBy = "vendor", fetch = FetchType.LAZY)
    private VendorLicense vendorLicense;

    public void changeInfo(String vendorName, String vendorEmail, String vendorPhoneNo) {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPhoneNo = vendorPhoneNo;
    }

    public void changePassword(String newPassword){
        vendorPassword = newPassword;
    }
}