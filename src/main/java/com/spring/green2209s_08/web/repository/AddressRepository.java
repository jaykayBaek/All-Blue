package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.member.id = :memberId order by a.id desc")
    List<Address> findAllByMemberId(@Param("memberId") Long memberId);

    Optional<Address> findByMemberIdAndId(Long memberId, Long addressId);
}
