package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
