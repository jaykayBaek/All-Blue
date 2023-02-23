package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VendorLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_license_id")
    private Long id;

    private String storeName;
    private String licenseNo;

    @Embedded
    private StoreAddress storeAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}
