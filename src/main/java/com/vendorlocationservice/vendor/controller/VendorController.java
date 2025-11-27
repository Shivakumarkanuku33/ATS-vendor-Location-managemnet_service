package com.vendorlocationservice.vendor.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendorlocationservice.vendor.dto.VendorRequest;
import com.vendorlocationservice.vendor.dto.VendorResponse;
import com.vendorlocationservice.vendor.service.VendorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorResponse> createVendor(@RequestBody VendorRequest request) {
        return new ResponseEntity<>(vendorService.createVendor(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendor(id));
    }

    @GetMapping
    public ResponseEntity<Page<VendorResponse>> listVendors(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(vendorService.searchVendors(search, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorResponse> updateVendor(
            @PathVariable Long id,
            @RequestBody VendorRequest request) {
        return ResponseEntity.ok(vendorService.updateVendor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}

