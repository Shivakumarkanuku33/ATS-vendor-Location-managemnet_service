package com.vendorlocationservice.location.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendorlocationservice.location.dto.LocationRequest;
import com.vendorlocationservice.location.dto.LocationResponse;
import com.vendorlocationservice.location.service.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationResponse> createLocation(@RequestBody LocationRequest request) {
        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        return ResponseEntity.ok(locationService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationResponse> updateLocation(
            @PathVariable Long id,
            @RequestBody LocationRequest request) {
        return ResponseEntity.ok(locationService.updateLocation(id, request));
    }
}

