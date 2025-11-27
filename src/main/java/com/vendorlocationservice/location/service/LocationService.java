package com.vendorlocationservice.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vendorlocationservice.location.dto.LocationRequest;
import com.vendorlocationservice.location.dto.LocationResponse;
import com.vendorlocationservice.location.entity.Location;
import com.vendorlocationservice.location.repository.LocationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationResponse createLocation(LocationRequest request) {
        Location parent = null;

        if (request.getParentId() != null) {
            parent = locationRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent location not found"));
        }

        Location location = Location.builder()
                .name(request.getName())
                .address(request.getAddress())
                .parent(parent)
                .status(Location.Status.ACTIVE)
                .build();

        locationRepository.save(location);
        return toResponse(location);
    }

    public LocationResponse getLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return toResponse(location);
    }

    public List<LocationResponse> listAll() {
        return locationRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public LocationResponse updateLocation(Long id, LocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        location.setName(request.getName());
        location.setAddress(request.getAddress());

        locationRepository.save(location);
        return toResponse(location);
    }

    private LocationResponse toResponse(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .address(location.getAddress())
                .status(location.getStatus().name())
                .parentId(location.getParent() != null ? location.getParent().getId() : null)
                .build();
    }
}

