package com.vendorlocationservice.vendor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vendorlocationservice.vendor.dto.VendorRequest;
import com.vendorlocationservice.vendor.dto.VendorResponse;
import com.vendorlocationservice.vendor.entity.Vendor;
import com.vendorlocationservice.vendor.repository.VendorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorResponse createVendor(VendorRequest request) {
        Vendor vendor = Vendor.builder()
                .vendorName(request.getVendorName())
                .contactPerson(request.getContactPerson())
                .phone(request.getPhone())
                .email(request.getEmail())
                .status(Vendor.Status.ACTIVE)
                .build();

        vendorRepository.save(vendor);

        return toResponse(vendor);
    }

    public VendorResponse getVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        return toResponse(vendor);
    }

    public Page<VendorResponse> searchVendors(String name, int page, int size) {
        Page<Vendor> vendors = vendorRepository.findByVendorNameContainingIgnoreCase(
                name == null ? "" : name, PageRequest.of(page, size)
        );

        return vendors.map(this::toResponse);
    }

    public VendorResponse updateVendor(Long id, VendorRequest request) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setVendorName(request.getVendorName());
        vendor.setContactPerson(request.getContactPerson());
        vendor.setPhone(request.getPhone());
        vendor.setEmail(request.getEmail());

        vendorRepository.save(vendor);
        return toResponse(vendor);
    }

    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setStatus(Vendor.Status.INACTIVE); // Soft delete
        vendorRepository.save(vendor);
    }

    private VendorResponse toResponse(Vendor vendor) {
        return VendorResponse.builder()
                .id(vendor.getId())
                .vendorName(vendor.getVendorName())
                .contactPerson(vendor.getContactPerson())
                .phone(vendor.getPhone())
                .email(vendor.getEmail())
                .status(vendor.getStatus().name())
                .build();
    }
}

