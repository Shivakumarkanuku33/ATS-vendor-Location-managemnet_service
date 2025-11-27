package com.vendorlocationservice.vendor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendorlocationservice.vendor.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Page<Vendor> findByVendorNameContainingIgnoreCase(String vendorName, Pageable pageable);

}
