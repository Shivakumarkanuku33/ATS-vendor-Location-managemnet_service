package com.vendorlocationservice.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendorlocationservice.location.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
